package io.specgen.minibus_booking_backend.services;

import io.specgen.minibus_booking_backend.entities.Role;
import io.specgen.minibus_booking_backend.entities.User;
import io.specgen.minibus_booking_backend.models.*;
import io.specgen.minibus_booking_backend.repositories.*;
import io.specgen.minibus_booking_backend.security.JwtUtils;
import io.specgen.minibus_booking_backend.security.UserDetailsImpl;
import io.specgen.minibus_booking_backend.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
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
	private PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Override
	public AuthDto authenticateUser(LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				loginDto.getUsername(),
				loginDto.getPassword())
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities()
			.stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.toList());

		return new AuthDto(
			jwt,
			"Bearer",
			userDetails.getId(),
			userDetails.getFirstName(),
			userDetails.getLastName(),
			userDetails.getUsername(),
			userDetails.getEmail(),
			roles
		);
	}

	@Override
	public void registerUser(RegisterDto body) {
		Role role = roleRepository.findByName("ROLE_USER").orElse(null);

		User user = new User(
			body.getFirstName(),
			body.getLastName(),
			body.getUsername(),
			body.getEmail(),
			encoder.encode(body.getPassword()),
			Set.of(Objects.requireNonNull(role))
		);

		userRepository.save(user);
	}
}
