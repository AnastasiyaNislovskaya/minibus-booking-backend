package io.specgen.minibus_booking.services;

import io.specgen.minibus_booking.converters.UserConverters;
import io.specgen.minibus_booking.entities.Role;
import io.specgen.minibus_booking.entities.User;
import io.specgen.minibus_booking.models.*;
import io.specgen.minibus_booking.repositories.RoleRepository;
import io.specgen.minibus_booking.repositories.AdminRepository;
import io.specgen.minibus_booking.services.admin.*;
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
	private AdminRepository adminRepository;

	@Autowired
	private UserConverters userConverters;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<UserDto> getAllUsers() {
		return adminRepository.findAll()
			.stream()
			.map(userConverters::userEntityToUserDto)
			.collect(Collectors.toList());
	}

	@Override
	public UserDto getById(long userId) {
		return userConverters.userEntityToUserDto(adminRepository.getById(userId));
	}

	@Override
	public void deleteUser(long userId) {
		adminRepository.deleteById(userId);
	}

	@Override
	public void createUser(RegisterDto body) {
		Role role = roleRepository.findByName("ROLE_USER").orElse(null);

		User user = new User(
			body.getFirstName(),
			body.getLastName(),
			body.getUsername(),
			body.getEmail(),
			passwordEncoder.encode(body.getPassword()),
			Set.of(Objects.requireNonNull(role))
		);

		adminRepository.save(user);
	}
}
