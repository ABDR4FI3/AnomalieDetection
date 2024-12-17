import React from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import "leaflet/dist/leaflet.css";

const center = [34.0522, -118.2437];

const CrimeMap: React.FC = () => (
  <div style={{ height: "100px", width: "100px" }}>
    <MapContainer center={center} zoom={10}>
      <TileLayer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap contributors</a>'
      />
      <Marker position={[34.0522, -118.2437]}>
        <Popup>A sample crime location.</Popup>
      </Marker>
    </MapContainer>
  </div>
);

export default CrimeMap;
