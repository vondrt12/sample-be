package eu.greyson.sample.shared.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class RegisterRequest {

    @NotBlank
    @Schema(description = "User first name")
    private String firstName;

    @NotBlank
    @Schema(description = "User last name")
    private String lastName;

    @NotBlank
    @Email
    @Schema(description = "User email")
    private String email;

    @NotBlank
    @Schema(description = "User password")
    private String password;

}
