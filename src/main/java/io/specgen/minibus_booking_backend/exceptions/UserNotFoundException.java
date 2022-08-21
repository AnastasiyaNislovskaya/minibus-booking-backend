package io.specgen.minibus_booking_backend.exceptions;


public class UserNotFoundException extends ResourceNotFoundException {

	private static final long serialVersionUID = -7428665705397767944L;

	public UserNotFoundException(long passengerId) {
		super("User", "user-id", passengerId);
	}

}
