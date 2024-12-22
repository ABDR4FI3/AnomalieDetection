import React, { useEffect, useState } from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import { LatLngExpression } from "leaflet";
import "leaflet/dist/leaflet.css";

import useCrimeData from "../hooks/useCrimeData";

const center: LatLngExpression = [34.0522, -118.2437];

const CrimeMap: React.FC = () => {
  const { data, loading } = useCrimeData();
  const [mapInitialized, setMapInitialized] = useState(false);

  useEffect(() => {
    if (!loading && !mapInitialized) {
      setMapInitialized(true); // Map has been initialized
    }
  }, [loading]);

  if (loading || !mapInitialized) {
    return (
      <div className="text-center mt-20 text-2xl font-semibold">Loading...</div>
    );
  }

  return (
    <div style={{ height: "100vh", width: "100%" }}>
      <MapContainer
        center={center}
        zoom={10}
        style={{ height: "1000px", width: "500px" }}
      >
        <TileLayer
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap contributors</a>'
        />
        {data.map((crime, index) => (
          <Marker key={index} position={[crime.lat, crime.lon]}>
            <Popup>
              <div className="text-sm">
                <strong>{crime.description}</strong>
                <br />
                Date: {crime.date}
                <br />
                Lat: {crime.lat}, Lon: {crime.lon}
              </div>
            </Popup>
          </Marker>
        ))}
      </MapContainer>
    </div>
  );
};

export default CrimeMap;
