package tech.schoolapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.schoolapi.core.config.modelMapper.ModelMapperService;
import tech.schoolapi.dto.request.user.UserUpdateRequest;
import tech.schoolapi.entity.User;
import tech.schoolapi.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapperService modelMapperService;

    @GetMapping()
    public ResponseEntity findAll() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity findUser(@PathVariable Long id) {
        return userService.findUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@RequestBody UserUpdateRequest user) {
        return userService.updateUser(modelMapperService.forRequest().map(user, User.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
