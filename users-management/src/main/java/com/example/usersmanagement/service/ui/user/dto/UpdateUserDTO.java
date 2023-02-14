package com.example.usersmanagement.service.ui.user.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 
 * @author benedetto.cosentino
 *
 */
public class UpdateUserDTO {

	@Size(min = 1, max = 255, message = "wrong id")
	private final String id;
	
	@Size(min = 1, max = 255, message = "firstName length is wrong")
	private final String firstName;
	
	@Size(min = 1, max = 255, message = "lastName length is wrong")
	private final String lastName;
	
	@Email
	@Size(min = 1, max = 255, message = "email length is wrong")
	private final String email;
	
	@Size(min = 1, max = 255, message = "address length is wrong")
	private final String address;


	@JsonCreator
	public UpdateUserDTO(
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
	
}
