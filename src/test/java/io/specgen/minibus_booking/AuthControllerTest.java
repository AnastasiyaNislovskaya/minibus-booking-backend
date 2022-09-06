package io.specgen.minibus_booking;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static io.specgen.minibus_booking.Utils.fixQuotes;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthControllerTest extends AbstractControllerTest {

	@Test
	void authenticate() throws Exception {
		var authUser = "{'username':'elena','password':'123456'}";

		perform(post("/auth/signin")
			.contentType(MediaType.APPLICATION_JSON)
			.content(fixQuotes(authUser)))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	void register() throws Exception {
		var newUser =
			"{'first_name':'Пётр','last_name':'Петров','phone':'+375291234567'" +
				",'username':'petrov','email':'petrov@mail.ru','password':'123456'}";

		perform(post("/auth/signup")
			.contentType(MediaType.APPLICATION_JSON)
			.content(fixQuotes(newUser)))
			.andDo(print())
			.andExpect(status().isCreated());
	}
}
