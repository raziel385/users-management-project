import 'dart:convert';

class User {
  String firstName;
  String lastName;
  String email;
  String id;
  String address;

  User(this.id, this.firstName, this.lastName, this.email, this.address);

  User.fromJson(Map<String, dynamic> json)
      : firstName = json['firstName'],
        lastName = json['lastName'],
        email = json['email'],
        address = json['address'] ?? "",
        id = json['id'];

  Map<String, dynamic> toJson() =>
      {'firstName': firstName, 'lastName': lastName, 'email': email, 'address': address, 'id': id};

  @override
  String toString() {
    return json.encode(toJson());
  }
}
