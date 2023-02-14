package com.example.usersmanagement.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.example.usersmanagement.model.shared.BaseEntity;

/**
 * 
 * @author benedetto.cosentino
 *
 */
@Entity
public class User extends BaseEntity{
	
    @Column
    @NotBlank(message = "firstName is mandatory")
	private String firstName;
	
    @Column
    @NotBlank(message = "lastName is mandatory")
	private String lastName;
	
    @Column
    @NotBlank(message = "email is mandatory")
    @Email
	private String email; 
    
    @Column
    @NotBlank(message = "address is mandatory")
	private String address; 
	
	private User(String firstName, String lastName, String email, String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;

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

	public void update (String firstName, String lastName, String email, String address) {
		super.update();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
	}
	
	public static User of(
			String firstName, 
			String lastName,
			String email,
			String address) {
		return new User(firstName, lastName, email, address);
	}
	
	// JPA
	protected User() {
	}
}
