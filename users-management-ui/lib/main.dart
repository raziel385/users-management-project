import 'package:bot_toast/bot_toast.dart';
import 'package:easy_search_bar/easy_search_bar.dart';
import 'package:file_picker/file_picker.dart';
import 'package:flutter/material.dart';
import 'package:global_configuration/global_configuration.dart';
import 'package:mime/mime.dart';
import 'package:users_management_ui/config/app_settings.config.dart';
import 'package:users_management_ui/locator.dart';
import 'dart:async';

import 'package:users_management_ui/model/user.dart';
import 'package:users_management_ui/resources/users_repository.dart';
import 'package:users_management_ui/ui/add_user.dart';
import 'package:users_management_ui/ui/user_detail.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner:false,
      builder: BotToastInit(),
      navigatorObservers: <NavigatorObserver>[BotToastNavigatorObserver()],
      title: 'Users Management Demo Home Page',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: const MyHomePage(title: 'Users Management Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  List<User> users = [];

  @override
  void initState() {
    super.initState();
    GlobalConfiguration().loadFromMap(dockerComposeSettings);
    setupLocator();
    loadList("");
  }

  @override
  Widget build(BuildContext context) {
    users ??= [];
    return Scaffold(
        appBar: EasySearchBar(
            title: Text(widget.title),
            onSearch: (value) {
              loadList(value);
            },
        ),
        body: Center(child: FutureBuilder(
          //future: loadList(),
          builder: (context, snapshot) {
            return users.isNotEmpty
                ? ListView.builder(
                    itemCount: users == null ? 0 : users.length,
                    itemBuilder: (BuildContext context, int index) {
                      return Card(
                          child: InkWell(
                        onTap: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: (context) =>
                                    DetailWidget(users[index])),
                          ).then((value) => Future.delayed(
                                  const Duration(milliseconds: 500), () {
                                loadList("");
                              }));
                        },
                        child: ListTile(
                          leading: const Icon(Icons.person),
                          title: Text(users[index].firstName),
                          subtitle: Text(users[index].lastName),
                        ),
                      ));
                    })
                : Center(
                    child: Text('No users found, tap plus button to add!',
                        style: Theme.of(context).textTheme.titleMedium));
          },
        )),
        floatingActionButton: Column(
          mainAxisAlignment: MainAxisAlignment.end,
          children: [
            FloatingActionButton(
              heroTag: "btn1",
              onPressed: () {
                _navigateToAddScreen(context);
              },
              child: const Icon(Icons.add),
            ),
            const SizedBox(
              height: 8,
            ),
            FloatingActionButton(
              heroTag: "btn2",
              onPressed: () {
                _uploadFile();
              },
              child: const Icon(Icons.checklist_sharp),
            ),
          ],
        ));
  }

  Future loadList(final String search) {
    Future<List<User>> futureCases = locator<UsersRepository>().getUsers(search);
    futureCases.then((usersList) {
      setState(() {
        users = usersList;
      });
    });
    return futureCases;
  }

  _navigateToAddScreen(BuildContext context) async {
    final result = await Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => const AddDataWidget()),
    ).then((value) => Future.delayed(const Duration(milliseconds: 500), () {
          loadList("");
        }));
  }

  _uploadFile() async {
    final filePicked = await FilePicker.platform.pickFiles();
    if (filePicked != null) {
      final file = filePicked.files.single; // PlatformFile
      final mimeType = lookupMimeType(file.name) ?? '';
      await locator<UsersRepository>()
          .uploadCSV(file.name, mimeType, file.bytes!);
      Future.delayed(const Duration(milliseconds: 500), () {
        loadList("");
      });
    }
  }
}
