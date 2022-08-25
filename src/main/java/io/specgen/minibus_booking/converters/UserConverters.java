package io.specgen.minibus_booking.converters;

import io.specgen.minibus_booking.entities.User;
import io.specgen.minibus_booking.models.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserConverters {
	@Autowired
	private RoleConverters roleConverters;

	public User userModelToUser(UserDto userDto) {
		return new User(
			userDto.getFirstName(),
			userDto.getLastName(),
			userDto.getUsername(),
			userDto.getEmail(),
			userDto.getPassword(),
			Set.copyOf(userDto.getRoles()
				.stream()
				.map(roleConverters::roleDtoToRole)
				.collect(Collectors.toList())
			)
		);
	}

	public UserDto userEntityToUserDto(User user) {
		return new UserDto(
			user.getId(),
			user.getFirstName(),
			user.getLastName(),
			user.getUsername(),
			user.getEmail(),
			user.getPassword(),
			List.copyOf(user.getRoles()
				.stream()
				.map(roleConverters::roleToRoleDto)
				.collect(Collectors.toList())
			)
		);
	}
}
