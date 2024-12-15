import 'package:flutter/material.dart';
import 'package:flutter_anomalie_detection/service/location_service.dart';


class DangerPage extends StatefulWidget {
  const DangerPage({super.key});

  @override
  _DangerPageState createState() => _DangerPageState();
}

class _DangerPageState extends State<DangerPage> {
  final LocationService _dangerService = LocationService();
  double _dangerPercentage = 0.0;

  Color _getColorForDanger(double dangerPercentage) {
    if (dangerPercentage <= 30) {
      return Colors.green; // Low danger
    } else if (dangerPercentage <= 70) {
      return Colors.orange; // Medium danger
    } else {
      return Colors.red; // High danger
    }
  }

  void _checkDanger() async {
    // Replace with actual coordinates
    double lat = 12.34;
    double lon = 56.78;

    try {
      double danger = await _dangerService.getDanger(lat, lon);
      setState(() {
        _dangerPercentage = danger;
      });
    } catch (e) {
      print('Error fetching danger percentage: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Danger Checker')),
      body: Padding(
        padding: const EdgeInsets.all(20.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: _checkDanger,
              child: const Text('Check Danger'),
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
      ),
    );
  }
}
