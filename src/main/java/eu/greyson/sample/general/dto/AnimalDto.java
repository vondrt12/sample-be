package eu.greyson.sample.general.dto;

import com.fasterxml.jackson.annotation.JsonView;
import eu.greyson.sample.general.enums.AnimalType;
import eu.greyson.sample.shared.json.DtoView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@JsonView(DtoView.Detail.class)
public class AnimalDto {

    @NotNull
    @Schema(description = "animal ID")
    private Long id;

    @NotBlank
    @Schema(description = "animal name")
    private String name;

    @NotNull
    @Schema(description = "type of animal")
    private AnimalType type;

    @NotNull
    @Schema(description = "animal color")
    private String color;

    @NotNull
    @Schema(description = "animal price")
    private BigDecimal price;

    @Schema(description = "animal note")
    private String note;
}
