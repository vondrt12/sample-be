package eu.greyson.sample.general.rest.impl;

import com.fasterxml.jackson.annotation.JsonView;
import eu.greyson.sample.general.dto.UserDto;
import eu.greyson.sample.general.rest.UserController;
import eu.greyson.sample.general.service.UserService;
import eu.greyson.sample.shared.json.DtoView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserControllerImpl implements UserController {
    private final UserService userService;
    @Override
    @GetMapping
    public ResponseEntity<UserDto> getUserDetail() {
        var user = userService.getUser(1L);
        return ResponseEntity.ok(user);
    }
}
