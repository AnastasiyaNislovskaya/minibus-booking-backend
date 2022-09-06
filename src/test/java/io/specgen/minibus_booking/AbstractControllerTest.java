package io.specgen.minibus_booking;

import io.specgen.minibus_booking.entities.User;
import io.specgen.minibus_booking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AbstractControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	protected ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
		return mockMvc.perform(builder);
	}

	public User findUser(String username) {
		return userRepository.findByUsername(username).orElseThrow(
			() -> new UsernameNotFoundException("User Not Found with username: " + username));
	}
}

