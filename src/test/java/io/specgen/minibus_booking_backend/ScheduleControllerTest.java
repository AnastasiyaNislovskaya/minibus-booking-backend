package io.specgen.minibus_booking_backend;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ScheduleControllerTest extends AbstractControllerTest {

	@Test
	void getAllTrips() throws Exception {
		perform(MockMvcRequestBuilders.get("/schedule/get_all_trips"))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
}

