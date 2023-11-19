package eu.greyson.sample.general.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionRequest {
    @NotNull
    @Schema(example = "CZ6508000000192000145399")
    private String debtor;
    @NotNull
    private int amount;
}
