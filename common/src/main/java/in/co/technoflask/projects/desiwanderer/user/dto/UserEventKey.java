package in.co.technoflask.projects.desiwanderer.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public record UserEventKey(@JsonProperty("id") UUID id) {}
