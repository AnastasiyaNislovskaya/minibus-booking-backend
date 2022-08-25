package io.specgen.minibus_booking.entities;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User passenger;

	@OneToOne
	@JoinColumn(name = "trip_schedule_id")
	private TripSchedule tripSchedule;

	public Ticket() {
	}

	public Ticket(User passenger, TripSchedule tripSchedule) {
		this.passenger = passenger;
		this.tripSchedule = tripSchedule;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getPassenger() {
		return passenger;
	}

	public void setPassenger(User passenger) {
		this.passenger = passenger;
	}

	public TripSchedule getTripSchedule() {
		return tripSchedule;
	}

	public void setTripSchedule(TripSchedule tripSchedule) {
		this.tripSchedule = tripSchedule;
	}
}
