package eu.greyson.sample.general.service;

import eu.greyson.sample.general.dto.AnimalDto;
import eu.greyson.sample.general.model.Animal;
import eu.greyson.sample.general.repository.AnimalRepository;
import eu.greyson.sample.shared.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalService extends BaseService<Animal, Long, AnimalRepository> implements InitializingBean {

    private final AnimalRepository animalRepository;
    private final ModelMapper mapper;


    @Override
    protected AnimalRepository getDefaultRepository() {
        return animalRepository;
    }

    public AnimalDto createDto(Animal store) {
        return mapper.map(store, AnimalDto.class);
    }

    @Override
    public void afterPropertiesSet() {
        mapper.addMappings(new AnimalDtoMapper());
    }

    private static class AnimalDtoMapper extends PropertyMap<Animal, AnimalDto> {
        @Override
        protected void configure() {
            map().setNote(source.getPublicNote());
        }
    }
}
