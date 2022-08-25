package io.specgen.minibus_booking.converters;

import io.specgen.minibus_booking.entities.Role;
import io.specgen.minibus_booking.models.RoleDto;
import org.springframework.stereotype.Component;

@Component
public class RoleConverters {
	public Role roleDtoToRole(RoleDto roleDto) {
		return new Role(
			roleDto.getName()
		);
	}

	public RoleDto roleToRoleDto(Role role) {
		return new RoleDto(
			role.getId(),
			role.getName()
		);
	}
}
