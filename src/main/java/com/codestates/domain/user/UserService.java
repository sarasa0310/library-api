package com.codestates.domain.user;

import com.codestates.domain.loanhistory.LoanHistory;
import com.codestates.domain.loanhistory.LoanHistoryRepository;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
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

    public void deleteUser(User user) {
        User foundUser = findVerifiedUser(user);

        verifyOnLoan(foundUser);

        userRepository.delete(foundUser);
    }

    @Transactional(readOnly = true)
    public List<LoanHistory> getLoanHistories(User user) {
        User foundUser = findVerifiedUser(user);

        return loanHistoryRepository.findAllByUser(foundUser);
    }

    @Transactional(readOnly = true)
    public User findVerifiedUser(User user) {
        return userRepository.findByNameAndPhone(user.getName(), user.getPhone())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public User findVerifiedUser(String name, String phone) {
        return userRepository.findByNameAndPhone(name, phone)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    private void verifyExistsUser(User user) {
        if (userRepository.existsByNameAndPhone(user.getName(), user.getPhone())) {
            throw new BusinessLogicException(ExceptionCode.USER_ALREADY_EXIST);
        }
    }

    private void verifyOnLoan(User user) {
        boolean isOnLoan = user.getLoanHistories()
                .stream()
                .anyMatch(loanHistory -> loanHistory.getReturnedAt() == null);

        if (isOnLoan) {
            throw new BusinessLogicException(ExceptionCode.ON_LOAN);
        }
    }

}
