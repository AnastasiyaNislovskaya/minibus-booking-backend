package io.specgen.minibus_booking.converters;

import io.specgen.minibus_booking.entities.Car;
import io.specgen.minibus_booking.models.CarDto;
import org.springframework.stereotype.Component;

@Component
public class CarConverters {
	public Car carDtoToCar(CarDto carDto) {
		return new Car(
			carDto.getNumber()
		);
	}

	public CarDto carToCarDto(Car car) {
		return new CarDto(
			car.getId(),
			car.getNumber()
		);
	}
}
