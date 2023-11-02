package eu.greyson.sample.general.dto;

import com.fasterxml.jackson.annotation.JsonView;
import eu.greyson.sample.shared.json.DtoView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
@Schema(description = "store information")
public class StoreDto {

    @NotNull
    @JsonView(DtoView.List.class)
    @Schema(description = "store ID")
    private Long storeId;

    @NotBlank
    @JsonView(DtoView.List.class)
    @Schema(description = "store name")
    private String storeName;

    @NotNull
    @JsonView(DtoView.List.class)
    @Schema(description = "store unique company number")
    private Integer companyNumber;

    @JsonView(DtoView.Detail.class)
    @Schema(description = "list of animals in the store")
    private List<AnimalDto> animals;
}
