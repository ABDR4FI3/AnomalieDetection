import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import CrimeMap from "./Components/CrimeMap";
import NotFound from "./Components/NotFound";
import TEstMap from "./Components/TestMap";

const App: React.FC = () => {
  return (
    <Router>
      <div className="flex flex-col min-h-screen">
        <header className="bg-blue-600 text-white p-4 text-center">
          <h1 className="text-3xl font-bold">Crime Map Dashboard</h1>
        </header>
        <main className="flex-grow">
          <Routes>
            <Route path="/" element={<CrimeMap />} />
            <Route path="/map" element={<TEstMap />} />

            <Route path="*" element={<NotFound />} />
          </Routes>
        </main>
        <footer className="bg-gray-800 text-white p-4 text-center">
          <p>&copy; 2024 Crime Data Tracker. All rights reserved.</p>
        </footer>
      </div>
    </Router>
  );
};

export default App;
