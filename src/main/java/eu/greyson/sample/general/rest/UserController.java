package eu.greyson.sample.general.rest;

import eu.greyson.sample.general.dto.UserDto;
import eu.greyson.sample.shared.swagger.ApiResponses_200_401_404_500;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "User", description = "User controller")
public interface UserController {
    @Operation(
            summary = "User detail endpoint",
            description = "This endpoint returns detailed information about user."
    )
    @ApiResponses_200_401_404_500
    ResponseEntity<UserDto> getUserDetail();
}
