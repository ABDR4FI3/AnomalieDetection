import 'package:flutter/material.dart';
import '../provider/location_provider.dart';

class LocationButton extends StatelessWidget {
  final LocationProvider locationProvider;

  const LocationButton({Key? key, required this.locationProvider})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: () {
        locationProvider.trackLocation();
        ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(content: Text('Tracking started')),
        );
      },
      child: const Text('Start Tracking'),
    );
  }
}
