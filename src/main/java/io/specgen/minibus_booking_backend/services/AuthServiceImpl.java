package io.specgen.minibus_booking_backend.services;

import io.specgen.minibus_booking_backend.converters.RoleConverters;
import io.specgen.minibus_booking_backend.entities.*;
import io.specgen.minibus_booking_backend.models.*;
import io.specgen.minibus_booking_backend.repositories.*;
import io.specgen.minibus_booking_backend.services.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service("AuthService")
public class AuthServiceImpl implements AuthService {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleConverters roleConverters;

	@Override
	public UserDto authenticateUser(LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				loginDto.getUsername(),
				loginDto.getPassword())
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		User userDetails = userRepository.findByUsernameOrEmail(loginDto.getUsername(), loginDto.getUsername()).orElseThrow(null);

		UserDto userDto = new UserDto();
		userDto.setId(userDetails.getId());
		userDto.setFirstName(userDetails.getFirstName());
		userDto.setLastName(userDetails.getLastName());
		userDto.setUsername(userDetails.getUsername());
		userDto.setEmail(userDetails.getEmail());

		List<RoleDto> roles = userDetails.getRoles()
			.stream()
			.map(roleConverters::roleToRoleDto)
			.collect(Collectors.toList());

		userDto.setRoles(roles);

		return userDto;
	}

	@Override
	public void registerUser(RegisterDto body) {
		Role role = roleRepository.findByName("ROLE_USER").orElse(null);

		User user = new User(
			body.getFirstName(),
			body.getLastName(),
			body.getUsername(),
			body.getEmail(),
			passwordEncoder.encode(body.getPassword()),
			Set.of(Objects.requireNonNull(role))
		);

		userRepository.save(user);
	}
}
