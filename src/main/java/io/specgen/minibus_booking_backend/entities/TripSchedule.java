package io.specgen.minibus_booking_backend.entities;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "tripSchedule")
public class TripSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private LocalTime departureTime;

	private LocalTime arrivalTime;

	private int fare;

	private int availableSeats;

	@OneToOne
	@JoinColumn(name = "trip_id")
	private Trip tripDetail;

	public TripSchedule() {
	}

	public TripSchedule(LocalTime departureTime, LocalTime arrivalTime, int fare, int availableSeats, Trip tripDetail) {
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.fare = fare;
		this.availableSeats = availableSeats;
		this.tripDetail = tripDetail;
	}

	public TripSchedule(LocalTime departureTime, LocalTime arrivalTime, int fare, int availableSeats) {
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.fare = fare;
		this.availableSeats = availableSeats;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Trip getTripDetail() {
		return tripDetail;
	}

	public void setTripDetail(Trip tripDetail) {
		this.tripDetail = tripDetail;
	}
}
