package com.example.usersmanagement.rest.application;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.usersmanagement.service.shared.IdentityDTO;
import com.example.usersmanagement.service.shared.PageDTO;
import com.example.usersmanagement.service.ui.user.UserService;
import com.example.usersmanagement.service.ui.user.dto.CreateUserDTO;
import com.example.usersmanagement.service.ui.user.dto.UpdateUserDTO;
import com.example.usersmanagement.service.ui.user.dto.UserDTO;

/**
 * 
 * @author benedetto.cosentino
 *
 */
@RestController
@RequestMapping(value = ApiController.BASE_API_PATH + "/users")
public class UserController extends ApiController{

	@Autowired
	private UserService userService;


	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public IdentityDTO create(@Valid
			@RequestBody CreateUserDTO dto) {
		return userService.create(dto);
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public IdentityDTO update(@Valid
			@RequestBody UpdateUserDTO dto) {
		return userService.update(dto);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserDTO get(@PathVariable String id) {
		return userService.get(id);
	}

	@GetMapping(value = "/",  produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PageDTO<UserDTO> findAll(@RequestParam(name = "containing", required = false) String containing,
			@RequestParam(name = "page", required = true, defaultValue = "0") int page,
			@RequestParam(name = "size", required = true, defaultValue = "100") int size,
			@RequestParam(name = "direction", required = false, defaultValue = "ASC") Sort.Direction direction,
			@RequestParam(name = "sort", required = false, defaultValue = "firstName") String sortColumn
			) {
		final Sort sort = sortColumn != null ? Sort.by(direction, sortColumn) : null;
		final PageRequest pageRequest =  PageRequest.of(page, size, sort);
		return userService.findAll(containing, pageRequest);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void delete(@PathVariable String id) {
		userService.delete(id);
	}

	@PostMapping("/upload")
	public void uploadFile(@RequestParam("file") MultipartFile file) {
		userService.saveCSV(file);
	}

}
