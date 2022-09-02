package io.specgen.minibus_booking.repositories;

import io.specgen.minibus_booking.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Ticket, Long> {
	Optional<Ticket> findById(Long id);
}
