import 'package:flutter/material.dart';
import 'package:users_management_ui/model/user.dart';
import 'package:users_management_ui/ui/user_detail.dart';

class UsersList extends StatelessWidget {

  final List<User> users;
  const UsersList({super.key, required this.users});


  @override
  Widget build(BuildContext context) {
    return
      ListView.builder(
          itemCount: users == null ? 0 : users.length,
          itemBuilder: (BuildContext context, int index) {
            return
              Card(
                  child: InkWell(
                    onTap: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => DetailWidget(users[index])),
                      );
                    },
                    child: ListTile(
                      leading: const Icon(Icons.person),
                      title: Text(users[index].firstName),
                      subtitle: Text(users[index].lastName),
                    ),
                  )
              );
          });
  }

}