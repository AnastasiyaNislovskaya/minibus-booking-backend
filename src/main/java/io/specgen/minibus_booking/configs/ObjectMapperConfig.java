package io.specgen.minibus_booking.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.specgen.minibus_booking.json.Json;
import org.springframework.context.annotation.*;

@Configuration
public class ObjectMapperConfig {
	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		Json.setupObjectMapper(objectMapper);
		return objectMapper;
	}
}
