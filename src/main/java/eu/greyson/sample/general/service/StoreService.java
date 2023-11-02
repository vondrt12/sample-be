package eu.greyson.sample.general.service;

import eu.greyson.sample.general.dto.StoreDto;
import eu.greyson.sample.general.model.Store;
import eu.greyson.sample.general.repository.StoreRepository;
import eu.greyson.sample.shared.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService extends BaseService<Store, Long, StoreRepository> implements InitializingBean {

    private final StoreRepository storeRepository;
    private final ModelMapper mapper;


    @Override
    protected StoreRepository getDefaultRepository() {
        return storeRepository;
    }

    public StoreDto getStore(Long storeId) {
        return createDto(getUnsafe(storeId));
    }

    public List<StoreDto> filterStores() {
        return storeRepository.findAll().stream().map(this::createDto).collect(Collectors.toList());
    }

    public StoreDto createDto(Store store) {
        return mapper.map(store, StoreDto.class);
    }

    @Override
    public void afterPropertiesSet() {
        mapper.addMappings(new StoreDtoMapper());
    }

    private static class StoreDtoMapper extends PropertyMap<Store, StoreDto> {
        @Override
        protected void configure() {
            map().setStoreId(source.getId());
            map().setStoreName(source.getName());
        }
    }
}
