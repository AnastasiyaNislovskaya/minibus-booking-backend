package io.specgen.minibus_booking_backend;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookingControllerTest extends AbstractControllerTest {

	@Test
	@WithUserDetails(value = "alexandrova@mail.ru")
	void bookTicket() throws Exception {
		perform(MockMvcRequestBuilders.post("/booking/book_ticket")
			.param("user_id", String.valueOf(12))
			.param("trip_schedule_id", String.valueOf(1)))
			.andExpect(status().isOk())
			.andDo(print());
	}
}
