package io.specgen.minibus_booking_backend.services;

import io.specgen.minibus_booking_backend.converters.UserConverters;
import io.specgen.minibus_booking_backend.entities.Role;
import io.specgen.minibus_booking_backend.entities.User;
import io.specgen.minibus_booking_backend.exceptions.ResourceNotFoundException;
import io.specgen.minibus_booking_backend.models.*;
import io.specgen.minibus_booking_backend.repositories.RoleRepository;
import io.specgen.minibus_booking_backend.repositories.UserRepository;
import io.specgen.minibus_booking_backend.services.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverters userConverters;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<UserDto> getAllUsers() {
		return userRepository.findAll()
			.stream()
			.map(userConverters::userEntityToUserDto)
			.collect(Collectors.toList());
	}

	@Override
	public void deleteUser(long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User", "id :", userId));

		userRepository.delete(user);
	}

	@Override
	public UserDto createUser(RegisterDto body) {
		Role role = roleRepository.findByName("ROLE_USER").orElse(null);

		User user = new User(
			body.getFirstName(),
			body.getLastName(),
			body.getUsername(),
			body.getEmail(),
			passwordEncoder.encode(body.getPassword()),
			Set.of(Objects.requireNonNull(role))
		);

		User savedUser = userRepository.save(user);

		return userConverters.userEntityToUserDto(savedUser);
	}
}
