package io.specgen.minibus_booking;

import io.specgen.minibus_booking.repositories.TicketsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TicketsControllerTest extends AbstractControllerTest {
	@Autowired
	private TicketsRepository ticketsRepository;

	@Test
	@WithUserDetails(value = "elena")
	void getAllTickets() throws Exception {
		perform(get("/tickets/get_all_tickets/" + 2))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	@WithUserDetails(value = "elena")
	void deleteTicket() throws Exception {
		perform(delete("/tickets/delete_ticket/" + 30))
			.andDo(print())
			.andExpect(status().isOk());
		Assertions.assertFalse(ticketsRepository.findById(30L).isPresent());
	}
}
