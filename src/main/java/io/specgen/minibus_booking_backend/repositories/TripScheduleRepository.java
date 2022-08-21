package io.specgen.minibus_booking_backend.repositories;

import io.specgen.minibus_booking_backend.entities.Trip;
import io.specgen.minibus_booking_backend.entities.TripSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripScheduleRepository extends JpaRepository<TripSchedule, Long> {
	List<TripSchedule> findByTripDetail(Trip trip);
	List<TripSchedule> findAll();
}
