package in.co.technoflask.projects.desiwanderer.post.dto;

import in.co.technoflask.projects.desiwanderer.validation.constraints.JsonNullableIsPresent;
import in.co.technoflask.projects.desiwanderer.validation.group.Create;
import jakarta.validation.valueextraction.Unwrapping;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;

public record PostReportPayload(
    @JsonNullableIsPresent(
            message = "Post ID is required",
            payload = {Unwrapping.Skip.class},
            groups = {Create.class})
        JsonNullable<UUID> postId) {}
