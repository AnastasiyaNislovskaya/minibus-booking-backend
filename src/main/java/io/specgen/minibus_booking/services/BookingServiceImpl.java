package io.specgen.minibus_booking.services;

import io.specgen.minibus_booking.entities.Ticket;
import io.specgen.minibus_booking.entities.TripSchedule;
import io.specgen.minibus_booking.entities.User;
import io.specgen.minibus_booking.repositories.BookingRepository;
import io.specgen.minibus_booking.repositories.TripScheduleRepository;
import io.specgen.minibus_booking.repositories.AdminRepository;
import io.specgen.minibus_booking.services.booking.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("BookingService")
public class BookingServiceImpl implements BookingService {
	@Autowired
	private AdminRepository userRepository;

	@Autowired
	private TripScheduleRepository tripScheduleRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public void bookTicket(long userId, long tripScheduleId) {
		User passenger = userRepository.findById(userId).orElse(null);
		TripSchedule trip = tripScheduleRepository.findById(tripScheduleId).orElse(null);

		Ticket ticket = new Ticket(passenger, trip);

		bookingRepository.save(ticket);
	}
}
