package com.codestates.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    // todo : UserPostDto 유효성 검증

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody UserPostDto userPostDto) {
        User user = userMapper.userPostDtoToUser(userPostDto);

        User createdUser = userService.createUser(user);

        return ResponseEntity.ok().body(createdUser);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> deleteUser(@PathVariable("user-id") Long userId) {
        userService.deleteUser(userId);

        return ResponseEntity.noContent().build();
    }

}
