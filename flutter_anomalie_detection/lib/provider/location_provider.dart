import 'dart:async';
import 'package:geolocator/geolocator.dart';
import '../service/location_service.dart';

class LocationProvider {
  final LocationService _locationService = LocationService();
  Position? _lastPosition;

  Future<void> trackLocation() async {
    Geolocator.getPositionStream(
      locationSettings: const LocationSettings(distanceFilter: 100),
    ).listen((Position position) {
      if (_lastPosition == null ||
          Geolocator.distanceBetween(
                _lastPosition!.latitude,
                _lastPosition!.longitude,
                position.latitude,
                position.longitude,
              ) >=
              100) {
        _lastPosition = position;
        _locationService.getDanger(position.latitude, position.longitude);
      }
    });
  }
}
