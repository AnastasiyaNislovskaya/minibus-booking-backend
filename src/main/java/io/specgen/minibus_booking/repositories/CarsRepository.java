package io.specgen.minibus_booking.repositories;

import io.specgen.minibus_booking.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepository extends JpaRepository<Car, Long> {
}
