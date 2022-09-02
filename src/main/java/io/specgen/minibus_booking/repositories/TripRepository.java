package io.specgen.minibus_booking.repositories;

import io.specgen.minibus_booking.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TripRepository extends JpaRepository<Trip, Long> {
	Trip findByDepartureAndArrivalAndTripDate(String departure, String arrival, LocalDate tripDate);
}
