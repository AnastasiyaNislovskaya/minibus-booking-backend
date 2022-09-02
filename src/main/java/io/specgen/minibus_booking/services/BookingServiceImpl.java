package io.specgen.minibus_booking.services;

import io.specgen.minibus_booking.entities.*;
import io.specgen.minibus_booking.repositories.*;
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
		if (passenger == null) {
			return;
		}
		TripSchedule tripSchedule = tripScheduleRepository.findById(tripScheduleId).orElse(null);
		if (tripSchedule == null) {
			return;
		}

		Ticket ticket = new Ticket(passenger, tripSchedule);
		bookingRepository.save(ticket);

		tripSchedule.reduceAvailableSeatsAmount();
		tripScheduleRepository.save(tripSchedule);
	}
}
