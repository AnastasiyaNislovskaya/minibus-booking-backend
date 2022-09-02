package io.specgen.minibus_booking.repositories;

import io.specgen.minibus_booking.entities.Trip;
import io.specgen.minibus_booking.entities.TripSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripScheduleRepository extends JpaRepository<TripSchedule, Long> {
	List<TripSchedule> findByTripDetail(Trip trip);

	List<TripSchedule> findAll();
}
