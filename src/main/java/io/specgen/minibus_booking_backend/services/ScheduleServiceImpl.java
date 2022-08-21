package io.specgen.minibus_booking_backend.services;

import io.specgen.minibus_booking_backend.converters.*;
import io.specgen.minibus_booking_backend.entities.*;
import io.specgen.minibus_booking_backend.models.*;
import io.specgen.minibus_booking_backend.repositories.*;
import io.specgen.minibus_booking_backend.services.schedule.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("ScheduleService")
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	private TripScheduleRepository tripScheduleRepository;

	@Autowired
	private TripScheduleConverters tripScheduleConverters;

//	@Override
//	public List<LocalityDto> getLocalities() {
//		return localityRepository.findAll()
//			.stream()
//			.map(localityConverters::localityToLocalityDto)
//			.collect(Collectors.toList());
//	}

	@Override
	public List<TripScheduleDto> getAllTrips() {
		return tripScheduleRepository.findAll()
			.stream()
			.map(tripScheduleConverters::tripScheduleToTripScheduleDto)
			.collect(Collectors.toList());
	}

//	@Override
//	public List<TripScheduleDto> getTrips(String departure, String arrival, LocalDate tripDate) {
//		Trip trip = tripRepository.findByArrivalAndAndDepartureAndAndTripDate(departure, arrival, tripDate);
//
//		List<TripSchedule> tripSchedules = tripScheduleRepository.findByTripDetail(trip);
//
//		return tripSchedules
//			.stream()
//			.map(tripScheduleConverters::tripScheduleToTripScheduleDto)
//			.collect(Collectors.toList());
//	}
}
