package io.specgen.minibus_booking;

import io.specgen.minibus_booking.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;

import static io.specgen.minibus_booking.Utils.fixQuotes;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AdminControllerTest extends AbstractControllerTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	@WithUserDetails(value = "admin")
	void getAllUsers() throws Exception {
		perform(get("/admin/get_all_users"))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	@WithUserDetails(value = "elena")
	void getAllUsersForbidden() throws Exception {
		perform(get("/admin/get_all_users"))
			.andExpect(status().isForbidden());
	}

	@Test
	@WithUserDetails(value = "admin")
	void createUser() throws Exception {
		String newUser =
			"{'first_name':'Екатерина','last_name':'Филиппова','phone':'+375299876543'," +
				"'username':'kate','email':'philippova@mail.ru','password':'123456'}";

		perform(post("/admin/create_user")
			.contentType(MediaType.APPLICATION_JSON)
			.content(fixQuotes(newUser)))
			.andDo(print())
			.andExpect(status().isCreated());
	}

	@Test
	@WithUserDetails(value = "admin")
	void deleteUser() throws Exception {
		var userId = findUser("elena").getId();

		perform(delete("/admin/delete_user/" + userId))
			.andDo(print())
			.andExpect(status().isOk());
		Assertions.assertFalse(userRepository.findById(userId).isPresent());
	}
}
