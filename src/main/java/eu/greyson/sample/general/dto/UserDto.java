package eu.greyson.sample.general.dto;

import eu.greyson.sample.shared.enums.AuthProvider;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Data
@Schema(description = "user information")
public class UserDto {

    @NotNull
    @Schema(description = "User ID")
    private Long id;

    @NotBlank
    @Schema(description = "User first name")
    private String firstName;

    @NotBlank
    @Schema(description = "User last name")
    private String lastName;

    @NotNull
    @Schema(description = "User birth date")
    private LocalDateTime birthDate;

}
