package eu.greyson.sample.general.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "store information")
public class AccountDto {
    @NotNull
    @Schema(description = "account ID")
    private Long id;

    @NotBlank
    @Schema(description = "account IBAN")
    private String IBAN;

    @NotBlank
    @Schema(description = "account name")
    private String name;

    @NotNull
    @Schema(description = "amount of money on the account")
    private int balance;

    @Size(min=3, message = "{validation.name.size.too_short}")
    @Size(min=3, message = "{validation.name.size.too_long}")
    private String currency;
}
