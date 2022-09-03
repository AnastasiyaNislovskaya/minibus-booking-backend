package io.specgen.minibus_booking;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookingControllerTest extends AbstractControllerTest {

	@Test
	@WithUserDetails(value = "elena")
	void bookTicket() throws Exception {
		perform(post("/booking/book_ticket")
			.param("user_id", String.valueOf(2))
			.param("trip_schedule_id", String.valueOf(1)))
			.andExpect(status().isOk())
			.andDo(print());
	}
}
