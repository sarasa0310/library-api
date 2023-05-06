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
    public ResponseEntity<?> signUp(@RequestBody @Valid UserPostDto userPostDto) {
        User user = userMapper.userPostDtoToUser(userPostDto);

        User createdUser = userService.createUser(user);

        // todo: userResponseDto 추가

        return ResponseEntity.created(
                URI.create("/users/" + createdUser.getId()))
                .body(createdUser);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> deleteUser(@PathVariable("user-id") Long userId) {
        userService.deleteUser(userId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{user-id}/loan")
    public ResponseEntity<?> getLoanHistories(@PathVariable("user-id") Long userId) {
        List<LoanHistory> loanHistories = userService.getLoanHistories(userId);

        List<LoanHistoryResponseDto> loanHistoryResponses =
                loanHistoryMapper.loanHistoriesToLoanHistoryResponses(loanHistories);

        return ResponseEntity.ok().body(loanHistoryResponses);
    }

}
