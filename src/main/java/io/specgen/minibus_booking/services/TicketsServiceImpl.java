package io.specgen.minibus_booking.services;

import io.specgen.minibus_booking.converters.TicketsConverters;
import io.specgen.minibus_booking.entities.User;
import io.specgen.minibus_booking.repositories.AdminRepository;
import io.specgen.minibus_booking.repositories.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.specgen.minibus_booking.models.*;
import io.specgen.minibus_booking.services.tickets.*;

import java.util.List;
import java.util.stream.Collectors;

@Service("TicketsService")
public class TicketsServiceImpl implements TicketsService {
	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private TicketsRepository ticketsRepository;

	@Autowired
	private TicketsConverters ticketsConverters;

	@Override
	public List<TicketDto> getById(long userId) {
		User user = adminRepository.findById(userId).orElse(null);

		return ticketsRepository.findByPassenger(user)
			.stream()
			.map(ticketsConverters::ticketToTicketDto)
			.collect(Collectors.toList());
	}

	@Override
	public void deleteTicket(long ticketId) {
		ticketsRepository.deleteById(ticketId);
	}
}
