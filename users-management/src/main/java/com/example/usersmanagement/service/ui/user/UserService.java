package com.example.usersmanagement.service.ui.user;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.example.usersmanagement.service.shared.IdentityDTO;
import com.example.usersmanagement.service.shared.PageDTO;
import com.example.usersmanagement.service.ui.user.dto.CreateUserDTO;
import com.example.usersmanagement.service.ui.user.dto.UpdateUserDTO;
import com.example.usersmanagement.service.ui.user.dto.UserDTO;

/**
 * 
 * @author benedetto.cosentino
 *
 */
public interface UserService {

	PageDTO<UserDTO> findAll(String search, Pageable pageable);
	
	UserDTO get(String id);
	
	IdentityDTO create (CreateUserDTO dto);
	
	IdentityDTO update (UpdateUserDTO dto);

	void delete (String id);
	
	void saveCSV (MultipartFile file);


}
