package io.specgen.minibus_booking.converters;

import io.specgen.minibus_booking.entities.Ticket;
import io.specgen.minibus_booking.models.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketConverters {
	@Autowired
	private UserConverters userConverters;

	@Autowired
	private TripScheduleConverters tripScheduleConverters;

	public Ticket ticketDtoToTicket(TicketDto ticketDto) {
		return new Ticket(
			userConverters.userModelToUser(ticketDto.getPassenger()),
			tripScheduleConverters.tripScheduleDtoToTripSchedule(ticketDto.getTripSchedule())
		);
	}

	public TicketDto ticketToTicketDto(Ticket ticket) {
		return new TicketDto(
			userConverters.userEntityToUserDto(ticket.getPassenger()),
			tripScheduleConverters.tripScheduleToTripScheduleDto(ticket.getTripSchedule())
		);
	}
}
