package io.specgen.minibus_booking_backend;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static io.specgen.minibus_booking_backend.Utils.fixQuotes;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthControllerTest extends AbstractControllerTest {
	@Test
	void authenticate() throws Exception {
		String authUser = "{'username':'alexandrova@mail.ru','password':'123456'}";
		String expectedUser = "{'id':12,'first_name':'Елена','last_name':'Александрова'," +
			"'username':'elena','email':'alexandrova@mail.ru','roles':[{'id':2,'name':'ROLE_USER'}]}";

		perform(MockMvcRequestBuilders.post("/auth/signin")
			.contentType(MediaType.APPLICATION_JSON)
			.content(fixQuotes(authUser)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json(fixQuotes(expectedUser)));
	}

	@Test
	void register() throws Exception {
		String newUser = "{'first_name':'Иван','last_name':'Иванов','username':'ivanov'," +
			"'email':'ivanov@mail.ru','password':'123456'}";
		perform(MockMvcRequestBuilders.post("/auth/signup")
			.contentType(MediaType.APPLICATION_JSON)
			.content(fixQuotes(newUser)))
			.andDo(print())
			.andExpect(status().isCreated());
	}
}
