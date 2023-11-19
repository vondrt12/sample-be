package eu.greyson.sample.general.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "card information")
public class CardDto {
    @NotNull
    @Schema(description = "Card ID")
    private Long id;

    @NotBlank
    @Schema(description = "Card name")
    private String name;

    @NotNull
    @Schema(description = "Card blocked status")
    private boolean blocked;

    @Schema(description = "Date when the card was blocked")
    private LocalDateTime dateLocked;

}
