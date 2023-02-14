import 'dart:convert';

class CreateUser {
  String firstName;
  String lastName;
  String email;
  String address;

  CreateUser(this.firstName, this.lastName, this.email, this.address);

  Map<String, dynamic> toJson() => {
        'firstName': firstName,
        'lastName': lastName,
        'email': email,
        'address': address,
      };

  @override
  String toString() {
    return json.encode(toJson());
  }
}
