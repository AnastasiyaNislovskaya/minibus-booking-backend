package io.specgen.minibus_booking.converters;

import io.specgen.minibus_booking.entities.Trip;
import io.specgen.minibus_booking.models.TripDto;
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
