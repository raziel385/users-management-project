package com.example.usersmanagement.service.ui.user;

import java.util.function.Function;

import com.example.usersmanagement.model.user.User;
import com.example.usersmanagement.service.ui.user.dto.CreateUserDTO;
import com.example.usersmanagement.service.ui.user.dto.UserDTO;

/**
 * 
 * @author benedetto.cosentino
 *
 */
public class UserMapper {
	
	public static final Function<User, UserDTO> TO_USER_DTO = (user) -> {
		return UserDTO.of(
				user.id(),
				user.getFirstName(), 
				user.getLastName(),
				user.getEmail(),
				user.getAddress());
	};
	
	public static final Function<CreateUserDTO, User> TO_USER = (userDTO) -> {
		return User.of(
				userDTO.getFirstName(), 
				userDTO.getLastName(),
				userDTO.getEmail(),
				userDTO.getAddress());
	};

}
