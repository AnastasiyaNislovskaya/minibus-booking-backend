package io.specgen.minibus_booking_backend.converters;

import io.specgen.minibus_booking_backend.entities.Trip;
import io.specgen.minibus_booking_backend.models.TripDto;
import org.springframework.stereotype.Component;

@Component
public class TripConverters {
	public Trip tripDtoToTrip(TripDto tripDto) {
		return new Trip(
			tripDto.getDeparture(),
			tripDto.getArrival(),
			tripDto.getTripDate()
		);
	}

	public TripDto tripToTripDto(Trip trip) {
		return new TripDto(
			trip.getId(),
			trip.getDeparture(),
			trip.getArrival(),
			trip.getTripDate()
		);
	}

}
