import 'package:flutter/material.dart';
import 'package:flutter_anomalie_detection/Pages/danger_page.dart';
import 'package:flutter_anomalie_detection/components/location_button.dart';
import './provider/location_provider.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    final LocationProvider locationProvider = LocationProvider();

    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: DangerPage()
      );
  }
}
