
import 'dart:typed_data';

abstract class RestClient {

  Future<dynamic> get(String url);

  Future<dynamic> post(String url, Map<String, dynamic> data);

  Future<dynamic> put(String url, Map<String, dynamic> data);

  Future<dynamic> delete(String url);

  Future<dynamic> upload(String url, final String fileName, final String mimeType, final Uint8List file);


}
