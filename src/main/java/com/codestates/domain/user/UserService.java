package com.codestates.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        verifyExistsUser(user);

        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    private void verifyExistsUser(User user) {
        if (userRepository.existsByNameAndPhone(user.getName(), user.getPhone())) {
            throw new IllegalArgumentException("이미 존재하는 사용자명과 휴대전화 번호 조합입니다.");
        }
    }

}
