package io.specgen.minibus_booking.converters;

import io.specgen.minibus_booking.entities.TripSchedule;
import io.specgen.minibus_booking.models.TripScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class TripScheduleConverters {
	@Autowired
	private TripConverters tripConverters;

	@Autowired
	private CarConverters carConverters;

	public TripSchedule tripScheduleDtoToTripSchedule(TripScheduleDto tripScheduleDto) {
		return new TripSchedule(
			LocalTime.parse(tripScheduleDto.getDepartureTime()),
			LocalTime.parse(tripScheduleDto.getDepartureTime()),
			tripScheduleDto.getFare(),
			tripScheduleDto.getAvailableSets(),
			tripConverters.tripDtoToTrip(tripScheduleDto.getTripDetail()),
			carConverters.carDtoToCar(tripScheduleDto.getCar())
		);
	}

	public TripScheduleDto tripScheduleToTripScheduleDto(TripSchedule tripSchedule) {
		return new TripScheduleDto(
			tripSchedule.getId(),
			tripSchedule.getDepartureTime().toString(),
			tripSchedule.getArrivalTime().toString(),
			tripSchedule.getFare(),
			tripSchedule.getAvailableSeats(),
			tripConverters.tripToTripDto(tripSchedule.getTripDetail()),
			carConverters.carToCarDto(tripSchedule.getCarNumber())
		);
	}
}
