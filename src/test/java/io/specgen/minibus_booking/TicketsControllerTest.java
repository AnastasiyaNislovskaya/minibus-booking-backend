package io.specgen.minibus_booking;

import io.specgen.minibus_booking.repositories.TicketsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
		var userId = findUser("elena").getId();

		perform(get("/tickets/get_all_tickets/" + userId))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	@WithUserDetails(value = "elena")
	void deleteTicket() throws Exception {
		var user = findUser("elena");
		var ticketId = ticketsRepository.findAllByPassenger(user).get(0).getId();

		perform(delete("/tickets/delete_ticket/" + ticketId))
			.andDo(print())
			.andExpect(status().isOk());
		Assertions.assertFalse(ticketsRepository.findById(ticketId).isPresent());
	}
}
