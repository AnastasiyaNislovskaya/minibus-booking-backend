package io.specgen.minibus_booking_backend.converters;

import io.specgen.minibus_booking_backend.entities.TripSchedule;
import io.specgen.minibus_booking_backend.models.TripScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class TripScheduleConverters {
	@Autowired
	private TripConverters tripConverters;

	public TripSchedule tripScheduleDtoToTripSchedule(TripScheduleDto tripScheduleDto) {
		return new TripSchedule(
			LocalTime.parse(tripScheduleDto.getDepartureTime()),
			LocalTime.parse(tripScheduleDto.getDepartureTime()),
			tripScheduleDto.getFare(),
			tripScheduleDto.getAvailableSets(),
			tripConverters.tripDtoToTrip(tripScheduleDto.getTripDetail())
		);
	}

	public TripScheduleDto tripScheduleToTripScheduleDto(TripSchedule tripSchedule) {
		return new TripScheduleDto(
			tripSchedule.getId(),
			tripSchedule.getDepartureTime().toString(),
			tripSchedule.getArrivalTime().toString(),
			tripSchedule.getFare(),
			tripSchedule.getAvailableSeats(),
			tripConverters.tripToTripDto(tripSchedule.getTripDetail())
		);
	}
}
