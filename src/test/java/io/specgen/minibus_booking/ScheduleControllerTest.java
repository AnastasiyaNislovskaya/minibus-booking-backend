package io.specgen.minibus_booking;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ScheduleControllerTest extends AbstractControllerTest {

	@Test
	void getAllTrips() throws Exception {
		perform(get("/schedule/get_all_trips"))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	void getTripsByParams() throws Exception {
		perform(get("/schedule/get_trips_by_params/Гомель/Минск/2022-09-03"))
			.andDo(print())
			.andExpect(status().isOk());
	}
}
