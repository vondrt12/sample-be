package eu.greyson.sample.general.service;
import eu.greyson.sample.general.dto.UserDto;
import eu.greyson.sample.general.model.User;
import eu.greyson.sample.general.repository.UserRepository;
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
public class UserService extends BaseService<User, Long, UserRepository> implements InitializingBean {
    private final UserRepository userRepository;
    private final ModelMapper mapper;


    @Override
    protected UserRepository getDefaultRepository() {
        return userRepository;
    }

    public UserDto getUser(Long userId) {
        return createDto(userRepository.findUnsafe(userId));
    }

    public List<UserDto> filterUsers() {
        return userRepository.findAll().stream().map(this::createDto).collect(Collectors.toList());
    }

    public UserDto createDto(User user) {
        return mapper.map(user, UserDto.class);
    }

    @Override
    public void afterPropertiesSet() {
        mapper.addMappings(new UserService.UserDtoMapper());
    }

    private static class UserDtoMapper extends PropertyMap<User, UserDto> {
        @Override
        protected void configure() {
            map().setId(source.getId());
            map().setLastName(source.getLastName());
            map().setFirstName(source.getFirstName());
            map().setBirthDate(source.getBirthDate());

        }
    }
}
