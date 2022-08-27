package io.specgen.minibus_booking.repositories;

import io.specgen.minibus_booking.entities.Ticket;
import io.specgen.minibus_booking.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketsRepository extends JpaRepository<Ticket, Long> {
	List<Ticket> findByPassenger(User passenger);
}