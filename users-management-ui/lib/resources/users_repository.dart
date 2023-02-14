import 'dart:typed_data';

import 'package:dio/dio.dart';

import '../locator.dart';
import '../model/create_user.dart';
import '../model/user.dart';
import '../networking/rest_client.dart';

class UsersRepository {

  Future<String?> createUser(CreateUser user) async{
    final Response<dynamic> id = await locator<RestClient>().post("/users/",{'firstName' : user.firstName, 'lastName' : user.lastName, 'email' : user.email, 'address' : user.address});
    return id.data['id'];
  }

  Future<User> getUser( String id) async {
    final user = await locator<RestClient>().get("/users/$id");
    return User.fromJson(user.data);
  }

  Future<List<User>> getUsers(String query) async {
    String url = "";
    if(query == "") {
      url = "/users/";
    }else{
      url = "/users/?containing=$query";
    }
    final patients = await locator<RestClient>().get(url);
    Iterable l = patients.data['content'];
    List<User> patientsList =
    l.map((model) => User.fromJson(model)).toList();
    return patientsList;
  }

  Future<String?> updateUser(User user) async{
    final Response<dynamic> id = await locator<RestClient>().put("/users/",{'id': user.id, 'firstName' : user.firstName, 'lastName' : user.lastName, 'email' : user.email, 'address' : user.address});
    return id.data['id'];
  }

  deleteUser(String id) async{
    await locator<RestClient>().delete("/users/$id");
  }

  uploadCSV(final String fileName, final String mimeType, final Uint8List file) async{
    await locator<RestClient>().upload("/users/upload", fileName, mimeType,file);
  }

}
