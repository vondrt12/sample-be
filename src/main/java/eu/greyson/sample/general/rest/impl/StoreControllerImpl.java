package eu.greyson.sample.general.rest.impl;

import com.fasterxml.jackson.annotation.JsonView;
import eu.greyson.sample.general.dto.StoreDto;
import eu.greyson.sample.general.rest.StoreController;
import eu.greyson.sample.general.service.StoreService;
import eu.greyson.sample.shared.json.DtoView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("store")
@RequiredArgsConstructor
public class StoreControllerImpl implements StoreController {

    private final StoreService storeService;

    @Override
    @GetMapping
    @JsonView(DtoView.List.class)
    public ResponseEntity<List<StoreDto>> getAllStores() {
        return ResponseEntity.ok(storeService.filterStores());
    }

    @Override
    @GetMapping("{storeId}")
    @JsonView(DtoView.Detail.class)
    public ResponseEntity<StoreDto> getStoreDetail(@PathVariable(name = "storeId") Long storeId) {
        var store = storeService.getStore(storeId);
        return ResponseEntity.ok(store);
    }
}
