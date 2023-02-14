
import 'dart:typed_data';

import 'package:bot_toast/bot_toast.dart';
import 'package:dio/dio.dart';

import 'package:global_configuration/global_configuration.dart';
import 'package:users_management_ui/networking/rest_client.dart';

import 'custom_exception.dart';


class RestClientImpl implements RestClient{
  late Dio _dio;

  RestClientImpl() {
    _dio = Dio();
    _dio.options.connectTimeout = 20000;
    _dio.options.receiveTimeout = 20000;
    _dio.options.baseUrl = GlobalConfiguration().get("apiBaseUrl");
    _dio.options.headers = {
     // 'Content-Type': 'application/json',
      'Accept': 'application/json',
      "Access-Control-Allow-Origin": "*",
    };


    _dio.interceptors.add(InterceptorsWrapper(
        onRequest:(options, handler) async{
          // Do something before request is sent

          return handler.next(options); //continue
          // If you want to resolve the request with some custom data，
          // you can resolve a `Response` object eg: `handler.resolve(response)`.
          // If you want to reject the request with a error message,
          // you can reject a `DioError` object eg: `handler.reject(dioError)`
        },
        onResponse:(response,handler) {
          // Do something with response data
          return handler.next(response); // continue
          // If you want to reject the request with a error message,
          // you can reject a `DioError` object eg: `handler.reject(dioError)`
        },
        onError: (DioError error, handler) {
          // Do something with response error
          BotToast.showSimpleNotification(
              title: "Error",
              subTitle: error.response?.data['message'],
              duration: const Duration(seconds: 10));
          return  handler.next(error);//continue
          // If you want to resolve the request with some custom data，
          // you can resolve a `Response` object eg: `handler.resolve(response)`.
        }
    ));


  }

  @override
  Future<dynamic> get(String url) async {
    _dio.options.headers['content-Type'] = 'application/json';
    final response = await _dio.get(url);
    return _response(response);
  }

  @override
  Future<dynamic> post(String url, Map<String, dynamic> data) async {
    _dio.options.headers['content-Type'] = 'application/json';
    final response = await _dio.post(url, data: data);
    return _response(response);
  }

  @override
  Future<dynamic> put(String url,Map<String, dynamic> data) async {
    _dio.options.headers['content-Type'] = 'application/json';
    final response = await _dio.put(url, data: data);
    return _response(response);
  }

  @override
  Future delete(String url) async{
    _dio.options.headers['content-Type'] = 'application/json';
    final response = await _dio.delete(url);
    return _response(response);
  }

  @override
  Future<dynamic> upload(String url, final String fileName, final String mimeType, final Uint8List file) async{
    final fields = {
      'file': MultipartFile.fromBytes(file  ?? [], filename: fileName),
      'Content-Type': mimeType,
    };
    final formData = FormData.fromMap(fields);
    final response = await _dio.post(url, data: formData, options: Options(
        contentType: mimeType
    ));
    return _response(response);

  }

  dynamic _response(Response response) {
    if(response == null) {
      throw FetchDataException('Error occured while Communication with Server');
    }

    switch (response.statusCode) {
      case 200:
        return response;
      case 400:
        throw BadRequestException(response.toString());
      case 401:
        throw BadRequestException(response.toString());
      case 403:
        throw UnauthorizedException(response.toString());
      case 500:
        throw FetchDataException(response.toString());
      default:
        throw FetchDataException(
            'Error occured while Communication with Server with StatusCode : ${response.statusCode}');
    }
  }

}
