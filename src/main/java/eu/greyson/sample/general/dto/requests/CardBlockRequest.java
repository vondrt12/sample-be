package eu.greyson.sample.general.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CardBlockRequest {
    @NotNull
    boolean blocked;
}
