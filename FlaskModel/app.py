import os
from flask import Flask, request, jsonify
import pandas as pd
import numpy as np
import joblib

app = Flask(__name__)

# Path to the static folder where the models are stored
BASE_DIR = os.path.abspath(os.path.dirname(__file__))
MODEL_PATH = os.path.join(BASE_DIR, 'static', 'kmeans_model.pkl')
SCALER_PATH = os.path.join(BASE_DIR, 'static', 'scaler.pkl')

# Load the saved model and scaler
kmeans = joblib.load(MODEL_PATH)
scaler = joblib.load(SCALER_PATH)

# Mock Analyzer class for storing cluster information
class Analyzer:
    def __init__(self, kmeans, scaler):
        self.cluster_centers_ = kmeans.cluster_centers_
        self.scaler = scaler

# Initialize analyzer
analyzer = Analyzer(kmeans, scaler)

# Load clustered data for density calculations
DATA_PATH = os.path.join(BASE_DIR, 'static', 'CleanedDataCrime.csv')
df_cleaned = pd.read_csv(DATA_PATH)[['LAT', 'LON']]
df_cleaned['Cluster'] = kmeans.labels_

# Function to calculate danger likelihood
def calculate_danger_likelihood(lat, lon, clustered_df, analyzer, density_weight=0.1, distance_weight=0.9):
    # Convert input coordinates to scaled values
    scaled_coords = analyzer.scaler.transform([[lat, lon]])[0]

    # Calculate distances to all centroids
    distances = np.linalg.norm(analyzer.cluster_centers_ - scaled_coords, axis=1)

    # Find the nearest cluster
    nearest_cluster = np.argmin(distances)
    nearest_distance = distances[nearest_cluster]

    # Danger likelihood based on proximity to centroid
    max_distance = np.max(distances)
    proximity_score = max(0, 1 - nearest_distance / max_distance)  # Linear decay

    # Density-based danger (optional, if density is provided)
    cluster_density = clustered_df[clustered_df['Cluster'] == nearest_cluster].shape[0]
    max_density = clustered_df['Cluster'].value_counts().max()
    density_score = cluster_density / max_density

    # Combine the scores
    danger_likelihood = (distance_weight * proximity_score + density_weight * density_score) * 100

    return danger_likelihood, nearest_cluster

@app.route('/predict', methods=['POST'])
def predict():
    # Parse incoming JSON data
    data = request.get_json()

    try:
        lat = float(data['latitude'])
        lon = float(data['longitude'])

        # Calculate danger likelihood
        danger_percentage, cluster_id = calculate_danger_likelihood(
            lat, lon, df_cleaned, analyzer
        )

        response = {
            'latitude': lat,
            'longitude': lon,
            'danger_percentage': round(danger_percentage, 2),
            'nearest_cluster': int(cluster_id)
        }

        return jsonify(response), 200

    except Exception as e:
        return jsonify({'error': str(e)}), 400

if __name__ == '__main__':
    app.run(debug=True)
