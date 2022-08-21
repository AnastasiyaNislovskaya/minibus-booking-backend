package io.specgen.minibus_booking_backend.services;

import io.specgen.minibus_booking_backend.entities.Ticket;
import io.specgen.minibus_booking_backend.entities.TripSchedule;
import io.specgen.minibus_booking_backend.entities.User;
import io.specgen.minibus_booking_backend.repositories.BookingRepository;
import io.specgen.minibus_booking_backend.repositories.TripScheduleRepository;
import io.specgen.minibus_booking_backend.repositories.UserRepository;
import io.specgen.minibus_booking_backend.services.booking.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("BookingService")
public class BookingServiceImpl implements BookingService {
	@Autowired
	private UserRepository userRepository;

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
