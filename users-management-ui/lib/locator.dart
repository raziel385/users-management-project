

import 'package:get_it/get_it.dart';
import 'package:users_management_ui/resources/users_repository.dart';

import 'networking/rest_client.dart';
import 'networking/rest_client_impl.dart';

GetIt locator = GetIt.instance;

void setupLocator(){
  //service

    locator.registerSingleton<RestClient>(RestClientImpl());

  //repository
  locator.registerSingleton<UsersRepository>(UsersRepository());

  //bloc

}