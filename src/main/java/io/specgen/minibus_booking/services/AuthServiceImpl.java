package io.specgen.minibus_booking.services;

import io.specgen.minibus_booking.entities.*;
import io.specgen.minibus_booking.models.*;
import io.specgen.minibus_booking.repositories.*;
import io.specgen.minibus_booking.security.*;
import io.specgen.minibus_booking.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
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
	private UserRepository adminRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

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
			userDetails.getPhone(),
			userDetails.getUsername(),
			userDetails.getEmail(),
			roles
		);
	}

	@Override
	public void registerUser(RegisterDto body) {
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
