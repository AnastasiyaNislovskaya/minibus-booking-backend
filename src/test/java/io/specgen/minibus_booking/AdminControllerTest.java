package io.specgen.minibus_booking;

import io.specgen.minibus_booking.repositories.AdminRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminControllerTest extends AbstractControllerTest {
	@Autowired
	private AdminRepository userRepository;

	@Test
	@WithUserDetails(value = "admin")
	void getAllUsers() throws Exception {
		perform(MockMvcRequestBuilders.get("/admin/get_all_users"))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	@WithUserDetails(value = "alexandrova@mail.ru")
	void getAllUsersForbidden() throws Exception {
		perform(MockMvcRequestBuilders.get("/admin/get_all_users"))
			.andExpect(status().isForbidden());
	}

	@Test
	@WithUserDetails(value = "admin")
	void deleteUser() throws Exception {
		perform(MockMvcRequestBuilders.delete("/admin/delete_user/" + 12))
			.andDo(print())
			.andExpect(status().isOk());
		Assertions.assertFalse(userRepository.findById(12L).isPresent());
		Assertions.assertTrue(userRepository.findById(1L).isPresent());
	}
}
