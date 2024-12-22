import 'dart:convert';

import 'package:http/http.dart' as http;

class LocationService {
  final String _baseUrl = 'http://10.0.2.2:8080/api/demo/position';
  
  Future<double> getDanger(double lat, double lon) async {
    try {
      final response = await http.post(
        Uri.parse(_baseUrl),
        headers: {'Content-Type': 'application/json'},
        body: json.encode({'latitude': lat, 'longitude': lon}),
      );

      if (response.statusCode == 200) {
        final responseData = json.decode(response.body);
        return responseData as double;
      } else {
        throw Exception('Failed to get danger value: ${response.body}');
      }
    } catch (e) {
      print('Error fetching danger value: $e');
      rethrow;
    }
  }
}
