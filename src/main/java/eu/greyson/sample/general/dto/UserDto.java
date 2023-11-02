package eu.greyson.sample.general.dto;

import eu.greyson.sample.shared.enums.AuthProvider;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
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

    @NotBlank
    @Schema(description = "User email name")
    private String email;

    @Schema(description = "User image url")
    private String imageUrl;

    @Schema(description = "Auth provider")
    private AuthProvider provider;
}
