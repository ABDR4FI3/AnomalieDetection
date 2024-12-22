import React from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import "leaflet/dist/leaflet.css";

// const center = [34.0522, -118.2437];

const TEstMap: React.FC = () => (
  // Height or width must be set for the map to display
  <div style={{ height: "100", width: "100" }}>
    <MapContainer
      center={[34.0522, -118.2437]}
      zoom={13}
      scrollWheelZoom={false}
      className="h-full"
      style={{ height: "100px", width: "100px" }}
    >
      <TileLayer
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
      <Marker position={[34.0522, -118.2437]}>
        <Popup>
          A pretty CSS3 popup. <br /> Easily customizable.
        </Popup>
      </Marker>
    </MapContainer>
  </div>
);

export default TEstMap;
