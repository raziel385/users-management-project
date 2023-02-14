package com.example.usersmanagement.service.ui.user.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 
 * @author benedetto.cosentino
 *
 */
public class UserDTO {

	private final String id;
	private final String firstName;
	private final String lastName;
	private final String email;
	private final String address;


	@JsonCreator
	public UserDTO(
			@JsonProperty("id") String id, 
			@JsonProperty("firstName") String firstName, 
			@JsonProperty("lastName") String lastName,
			@JsonProperty("email") String email,
			@JsonProperty("address") String address
			) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;

	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}
	
	public String getAddress() {
		return address;
	}

	public static UserDTO of(
			String id,
			String firstName, 
			String lastName,
			String email,
			String address) {
		return new UserDTO(id, firstName, lastName, email, address);
	}

}
