package io.specgen.minibus_booking.services;

import io.specgen.minibus_booking.converters.*;
import io.specgen.minibus_booking.entities.Trip;
import io.specgen.minibus_booking.models.*;
import io.specgen.minibus_booking.repositories.*;
import io.specgen.minibus_booking.services.schedule.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service("ScheduleService")
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	private TripScheduleRepository tripScheduleRepository;

	@Autowired
	private TripRepository tripRepository;

	@Autowired
	private TripScheduleConverters tripScheduleConverters;

	@Override
	public List<TripScheduleDto> getAllTrips() {
		return tripScheduleRepository.findAll()
			.stream()
			.map(tripScheduleConverters::tripScheduleToTripScheduleDto)
			.collect(Collectors.toList());
	}

	public List<TripScheduleDto> getTripsByParams(String departure, String arrival, LocalDate tripDate) {
		Trip trip = tripRepository.findByDepartureAndArrivalAndTripDate(departure, arrival, tripDate);

		return tripScheduleRepository.findByTripDetail(trip)
			.stream()
			.map(tripScheduleConverters::tripScheduleToTripScheduleDto)
			.collect(Collectors.toList());
	}
}
