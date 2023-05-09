package com.codestates.domain.user;

import com.codestates.domain.loanhistory.LoanHistory;
import com.codestates.domain.loanhistory.LoanHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final LoanHistoryRepository loanHistoryRepository;

    public User createUser(User user) {
        verifyExistsUser(user);

        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        User foundUser = userRepository.findById(userId).orElseThrow();

        verifyOnLoan(foundUser);

        userRepository.deleteById(userId);
    }

    @Transactional(readOnly = true)
    public List<LoanHistory> getLoanHistories(Long userId) {
        return loanHistoryRepository.findAllByUser_Id(userId);
    }

    private void verifyExistsUser(User user) {
        if (userRepository.existsByNameAndPhone(user.getName(), user.getPhone())) {
            throw new RuntimeException("이미 존재하는 사용자명과 휴대전화 번호 조합입니다.");
        }
    }

    private void verifyOnLoan(User user) {
        boolean isOnLoan = user.getLoanHistories()
                .stream()
                .anyMatch(loanHistory -> loanHistory.getReturnedAt() == null);

        if (isOnLoan) {
            throw new RuntimeException("대출 중인 책이 있을 경우 삭제 할 수 없습니다.");
        }
    }

}
