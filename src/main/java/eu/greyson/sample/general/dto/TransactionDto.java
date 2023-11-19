package eu.greyson.sample.general.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "transaction information")
public class TransactionDto {
    @NotNull
    @Schema(name = "Transaction ID")
    private Long id;

    @NotNull
    @Schema(name = "Amount")
    private int amount;

    @NotBlank
    @Schema(name = "Creditor")
    private String creditor;

    @NotBlank
    @Schema(name = "Debtor")
    private String debtor;

    @NotNull
    @Schema(name = "Transaction creation date")
    private LocalDateTime dateCreated;

    @Schema(name = "Transaction execution date")
    private LocalDateTime dateExecuted;

}
