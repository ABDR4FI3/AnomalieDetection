import 'package:flutter/material.dart';
import 'package:flutter_anomalie_detection/service/location_service.dart';
import 'package:geolocator/geolocator.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';

class DangerPage extends StatefulWidget {
  const DangerPage({super.key});

  @override
  _DangerPageState createState() => _DangerPageState();
}

class _DangerPageState extends State<DangerPage> {
  final LocationService _dangerService = LocationService();
  double _dangerPercentage = 0.0;
  Position? _currentPosition;
  double _zoomLevel = 5.0; // Initial zoom level

  Color _getColorForDanger(double dangerPercentage) {
    if (dangerPercentage <= 30) {
      return Colors.green; // Low danger
    } else if (dangerPercentage <= 70) {
      return Colors.orange; // Medium danger
    } else {
      return Colors.red; // High danger
    }
  }

  Future<void> _checkDanger(double lat, double lon) async {
    try {
      double danger = await _dangerService.getDanger(lat, lon);
      setState(() {
        _dangerPercentage = danger;
      });
    } catch (e) {
      print('Error fetching danger percentage: $e');
    }
  }

  Future<void> _startLocationUpdates() async {
    bool serviceEnabled;
    LocationPermission permission;

    // Check if GPS is enabled
    serviceEnabled = await Geolocator.isLocationServiceEnabled();
    if (!serviceEnabled) {
      return Future.error('Location services are disabled.');
    }

    // Check for permissions
    permission = await Geolocator.checkPermission();
    if (permission == LocationPermission.denied) {
      permission = await Geolocator.requestPermission();
      if (permission == LocationPermission.denied) {
        return Future.error('Location permissions are denied.');
      }
    }

    if (permission == LocationPermission.deniedForever) {
      return Future.error(
          'Location permissions are permanently denied. Cannot request permissions.');
    }

    // Start listening to location updates
    Geolocator.getPositionStream(
      locationSettings: const LocationSettings(
        accuracy: LocationAccuracy.high,
        distanceFilter: 100, // Update every 100 meters
      ),
    ).listen((Position position) {
      setState(() {
        _currentPosition = position;
      });

      // Call the danger service with the updated location
      _checkDanger(position.latitude, position.longitude);
    });
  }

  @override
  void initState() {
    super.initState();
    _startLocationUpdates();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Danger Checker')),
      body: Stack(
        children: [
          Column(
            children: [
              Expanded(
                child: FlutterMap(
                  options: MapOptions(
                    initialCenter: _currentPosition != null
                        ? LatLng(_currentPosition!.latitude,
                            _currentPosition!.longitude)
                        : LatLng(37.7749,
                            -122.4194), // Default to a visible location
                    initialZoom: _zoomLevel, // Bind zoom level
                  ),
                  children: [
                    TileLayer(
                      urlTemplate:
                          'https://tile.openstreetmap.org/{z}/{x}/{y}.png', // Updated URL
                    ),
                    if (_currentPosition != null)
                      MarkerLayer(
                        markers: [
                          Marker(
                            point: LatLng(
                              _currentPosition!.latitude,
                              _currentPosition!.longitude,
                            ),
                            child: const Icon(
                              Icons.location_on,
                              color: Colors.red,
                              size: 40.0,
                            ),
                          ),
                        ],
                      ),
                  ],
                ),
              ),
              const SizedBox(height: 20),
              Container(
                width: 200,
                height: 200,
                decoration: BoxDecoration(
                  color: _getColorForDanger(_dangerPercentage),
                  borderRadius: BorderRadius.circular(10),
                ),
                child: Center(
                  child: Text(
                    '${_dangerPercentage.toStringAsFixed(1)}%',
                    style: const TextStyle(fontSize: 24, color: Colors.white),
                  ),
                ),
              ),
            ],
          ),
          Positioned(
            bottom: 250,
            right: 20,
            child: Column(
              children: [
                FloatingActionButton(
                  heroTag: 'zoomIn',
                  onPressed: () {
                    setState(() {
                      _zoomLevel = (_zoomLevel + 1)
                          .clamp(1.0, 18.0); // Clamp to valid zoom range
                    });
                  },
                  child: const Icon(Icons.add),
                ),
                const SizedBox(height: 10),
                FloatingActionButton(
                  heroTag: 'zoomOut',
                  onPressed: () {
                    setState(() {
                      _zoomLevel = (_zoomLevel - 1)
                          .clamp(1.0, 18.0); // Clamp to valid zoom range
                    });
                  },
                  child: const Icon(Icons.remove),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
