import pickle
import numpy as np
from flask import Flask, request, jsonify, Response
import pandas as pd
from flask_cors import CORS

app = Flask(__name__)
CORS(app)  # Enable CORS for all routes

# Define the paths to the model and scaler
MODEL_PATH = "static/kmeans_model.pkl"
SCALER_PATH = "static/scaler.pkl"
DATA_PATH = "static/crime_data.pkl"  # Pickled crime data

# Step 1: Load the pre-trained model and scaler from the specified paths
try:
    # Test loading the scaler
    with open(SCALER_PATH, 'rb') as scaler_file:
        scaler = pickle.load(scaler_file)
    print("Scaler loaded successfully:", scaler)

    # Test loading the KMeans model
    with open(MODEL_PATH, 'rb') as model_file:
        kmeans = pickle.load(model_file)
    print("KMeans model loaded successfully:", kmeans)

except Exception as e:
    print("Error loading file:", e)

# Step 2: Load the crime data from the pickled file
try:
    crime_data = pd.read_pickle(DATA_PATH)
    print("Crime data loaded successfully!")
except Exception as e:
    print("Error loading crime data:", e)


# Analyzer class for simplicity
class Analyzer:
    def __init__(self, kmeans, scaler):
        self.cluster_centers_ = kmeans.cluster_centers_
        self.scaler = scaler

analyzer = Analyzer(kmeans, scaler)

#Step 3 :
@app.route('/crime_data', methods=['GET'])
def get_crime_data():
    try:
        # Get page number and size from query parameters
        page = int(request.args.get('page', 1))  # Default page is 1
        page_size = int(request.args.get('page_size', 20000))  # Default page size is 20000

        # Calculate start and end indices
        start_idx = (page - 1) * page_size
        end_idx = start_idx + page_size

        # Slice the data
        data_slice = crime_data.iloc[start_idx:end_idx].to_dict(orient='records')

        # Prepare response
        response = {
            'page': page,
            'page_size': page_size,
            'total_records': len(crime_data),
            'data': data_slice
        }

        return jsonify(response), 200
    except Exception as e:
        return jsonify({'error': str(e)}), 500
@app.route('/crime_data_stream', methods=['GET'])
def get_crime_data_stream():
    def generate():
        for _, row in crime_data.iterrows():
            yield f"{row.to_json()}\n"

    return Response(generate(), mimetype='application/json')

# Step 4: Danger likelihood calculation function
def calculate_danger_likelihood(lat, lon, analyzer, density_weight=0.1, distance_weight=0.9):
    scaled_coords = analyzer.scaler.transform([[lat, lon]])[0]

    # Calculate distances to all centroids
    distances = np.linalg.norm(analyzer.cluster_centers_ - scaled_coords, axis=1)
    nearest_cluster = np.argmin(distances)
    nearest_distance = distances[nearest_cluster]

    max_distance = np.max(distances)
    proximity_score = max(0, 1 - nearest_distance / max_distance)

    # For simplicity, we omit density score since we don't have a loaded dataset
    density_score = 1  # Assume uniform density

    danger_likelihood = (distance_weight * proximity_score + density_weight * density_score) * 100
    return danger_likelihood, nearest_cluster

# Step 3: Flask API endpoint
@app.route('/danger', methods=['POST'])
def get_danger_level():
    try:
        data = request.get_json()
        lat = float(data['lat'])
        lon = float(data['lon'])

        # Calculate danger percentage
        danger_percentage, cluster_id = calculate_danger_likelihood(lat, lon, analyzer)

        response = {
            'latitude': lat,
            'longitude': lon,
            'cluster_id': int(cluster_id),
            'danger_percentage': round(danger_percentage, 2)
        }
        return jsonify(response), 200

    except Exception as e:
        return jsonify({'error': str(e)}), 400

if __name__ == '__main__':
    app.run(debug=True)
