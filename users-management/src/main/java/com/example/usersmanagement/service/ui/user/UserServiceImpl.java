package com.example.usersmanagement.service.ui.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.data.jpa.domain.Specification.where;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.example.usersmanagement.model.shared.DomainUtils;
import com.example.usersmanagement.model.user.User;
import com.example.usersmanagement.model.user.UserRepository;
import com.example.usersmanagement.model.user.UserSpecifications;
import com.example.usersmanagement.service.shared.IdentityDTO;
import com.example.usersmanagement.service.shared.PageDTO;
import com.example.usersmanagement.service.ui.user.dto.CreateUserDTO;
import com.example.usersmanagement.service.ui.user.dto.UpdateUserDTO;
import com.example.usersmanagement.service.ui.user.dto.UserDTO;
import com.example.usersmanagement.service.ui.user.utils.CSVHelper;
import com.google.common.base.Strings;

/**
 * 
 * @author benedetto.cosentino
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;

	@Transactional(readOnly = true)
	@Override
	public PageDTO<UserDTO> findAll(String search, Pageable pageable) {
		Specification<User> specification = where(UserSpecifications.valid());

		if(!Strings.isNullOrEmpty(search)) {
			specification = specification.and(UserSpecifications.containing(search));
		}

		final Page<User> usersPage = userRepository.findAll(specification, pageable);

		List<UserDTO> usersDTOs = usersPage
				.getContent()
				.stream()
				.map(u -> UserDTO.of(u.id(),u.getFirstName(), u.getLastName(), u.getEmail(), u.getAddress()))
				.collect(Collectors.toList());

		return PageDTO.of(usersDTOs);
	}

	@Transactional(readOnly = true)
	@Override
	public UserDTO get(String id) {
		User user = userRepository.findExisting(id);
		DomainUtils.checkCondition(user.isValid(), "User not found");
		return UserMapper.TO_USER_DTO.apply(user);
	}

	@Transactional(readOnly = false)
	@Override
	public IdentityDTO create(CreateUserDTO dto) {
		User user = UserMapper.TO_USER.apply(dto);
		userRepository.save(user);
		return IdentityDTO.of(user.id());
	}

	@Transactional(readOnly = false)
	@Override
	public IdentityDTO update(UpdateUserDTO dto) {
		User user = userRepository.findExisting(dto.getId());
		DomainUtils.checkCondition(user.isValid(), "User not found");
		user.update(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getAddress());
		return IdentityDTO.of(user.id());
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(String id) {
		User user = userRepository.findExisting(id);
		DomainUtils.checkCondition(user.isValid(), "User not found");
		user.delete();
	}

	@Transactional(readOnly = false)
	@Override
	public void saveCSV(MultipartFile file) {
		try {
			if (CSVHelper.hasCSVFormat(file)) {

				List<CreateUserDTO> usersDTOs = CSVHelper.csvToUsers(file.getInputStream());

				List<User> users = usersDTOs
						.stream()
						.map(u -> UserMapper.TO_USER.apply(u))
						.collect(Collectors.toList());

				userRepository.saveAll(users);
			}else {
				throw new IllegalArgumentException("Please upload a csv file!");
			}
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}

	}

}
