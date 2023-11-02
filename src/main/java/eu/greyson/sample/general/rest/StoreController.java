package eu.greyson.sample.general.rest;

import eu.greyson.sample.general.dto.StoreDto;
import eu.greyson.sample.shared.swagger.ApiResponses_200_400_401_500;
import eu.greyson.sample.shared.swagger.ApiResponses_200_401_404_500;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import jakarta.validation.constraints.NotNull;
import java.util.List;

@Tag(name = "Animal", description = "Animal controller")
public interface StoreController {

    @Operation(
            summary = "List of stores endpoint",
            description = "This endpoint returns list of all stores."
    )
    @ApiResponses_200_400_401_500
    ResponseEntity<List<StoreDto>> getAllStores();

    @Operation(
            summary = "Store detail endpoint",
            description = "This endpoint returns detailed information about store."
    )
    @ApiResponses_200_401_404_500
    ResponseEntity<StoreDto> getStoreDetail(@NotNull Long id);
}
