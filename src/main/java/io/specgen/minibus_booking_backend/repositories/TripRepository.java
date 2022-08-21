package io.specgen.minibus_booking_backend.repositories;

import io.specgen.minibus_booking_backend.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TripRepository extends JpaRepository<Trip, Long> {
	Trip findByArrivalAndAndDepartureAndAndTripDate(String departure, String arrival, LocalDate tripDate);
}
