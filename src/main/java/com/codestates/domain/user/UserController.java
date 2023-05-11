package com.codestates.domain.user;

import com.codestates.domain.loanhistory.LoanHistory;
import com.codestates.domain.loanhistory.LoanHistoryMapper;
import com.codestates.domain.loanhistory.LoanHistoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final LoanHistoryMapper loanHistoryMapper;

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody @Valid UserRequestDto userRequestDto) {
        User user = userMapper.userRequestDtoToUser(userRequestDto);

        User createdUser = userService.createUser(user);

        UserResponseDto userResponse = userMapper.userToUserResponseDto(createdUser);

        return ResponseEntity.created(
                URI.create("/users/" + createdUser.getId()))
                .body(userResponse);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        User user = userMapper.userRequestDtoToUser(userRequestDto);

        userService.deleteUser(user);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/loan")
    public ResponseEntity<?> getLoanHistories(@RequestBody @Valid UserRequestDto userRequestDto) {
        User user = userMapper.userRequestDtoToUser(userRequestDto);

        List<LoanHistory> loanHistories = userService.getLoanHistories(user);

        List<LoanHistoryResponseDto> loanHistoryResponses =
                loanHistoryMapper.loanHistoriesToLoanHistoryResponses(loanHistories);

        return ResponseEntity.ok().body(loanHistoryResponses);
    }

}
