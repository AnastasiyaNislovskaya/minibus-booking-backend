package io.specgen.minibus_booking.services;

import io.specgen.minibus_booking.converters.UserConverters;
import io.specgen.minibus_booking.entities.*;
import io.specgen.minibus_booking.models.*;
import io.specgen.minibus_booking.repositories.*;
import io.specgen.minibus_booking.services.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
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
	public void deleteUser(long userId) {
		adminRepository.deleteById(userId);
	}

	@Override
	public void createUser(RegisterDto body) {
		Role role = roleRepository.findByName("ROLE_USER").orElse(null);
		if (role == null) {
			return;
		}

		User user = new User(
			body.getFirstName(),
			body.getLastName(),
			body.getPhone(),
			body.getUsername(),
			body.getEmail(),
			passwordEncoder.encode(body.getPassword()),
			Set.of(role)
		);

		adminRepository.save(user);
	}
}
