package com.codestates.config;

import com.codestates.domain.book.Book;
import com.codestates.domain.book.BookRepository;
import com.codestates.domain.user.User;
import com.codestates.domain.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(BookRepository bookRepository, UserRepository userRepository) {
        return args -> {
            initBookRepository(bookRepository);
            initUserRepository(userRepository);
        };
    }

    private static void initBookRepository(BookRepository bookRepository) {
        bookRepository.save(new Book("세이노의 가르침", "세이노", "데이원"));
        bookRepository.save(new Book("흔한남매 13", "백난도", "미래엔아이세움"));
        bookRepository.save(new Book("사장학개론", "김승호", "스노우폭스북스"));
        bookRepository.save(new Book("나의 돈 많은 고등학교 친구", "송희구", "서삼독"));
        bookRepository.save(new Book("김미경의 마흔 수업", "김미경", "어웨이크북스"));

        bookRepository.save(new Book("건강과 다이어트를 동시에 잡는 7대 3의 법칙 채소·과일식", "조승우", "바이북스"));
        bookRepository.save(new Book("스즈메의 문단속", "신카이 마코토", "대원씨아이"));
        bookRepository.save(new Book("장하준의 경제학 레시피", "장하준", "부키"));
        bookRepository.save(new Book("메리골드 마음 세탁소", "윤정은", "북로망스"));
        bookRepository.save(new Book("모든 삶은 흐른다", "로랑스 드빌레르", "피카"));

        bookRepository.save(new Book("주술회전 22 더블특장판", "아쿠타미 게게", "서울문화사"));
        bookRepository.save(new Book("그리스 로마 신화 33", "박시연", "아울북"));
        bookRepository.save(new Book("이은경쌤의 초등어휘일력 365", "이은경", "포레스트북스"));
        bookRepository.save(new Book("내면소통", "김주환", "인플루엔셜"));
        bookRepository.save(new Book("고래", "천명관", "문학동네"));

        bookRepository.save(new Book("몰입 Think hard!", "황농문", "알에이치코리아"));
        bookRepository.save(new Book("아버지의 해방일지", "정지아", "창비"));
        bookRepository.save(new Book("불편한 편의점", "김호연", "나무옆의자"));
        bookRepository.save(new Book("흔한남매 과학 탐험대 7 생물 1", "김언정", "주니어김영사"));
        bookRepository.save(new Book("역행자", "자청", "웅진지식하우스"));

        bookRepository.findAll().forEach(book -> log.info("Preloaded Book: " + book));
    }

    private static void initUserRepository(UserRepository userRepository) {
        userRepository.save(new User("김코딩", "010-1234-5678"));
        userRepository.save(new User("나해커", "010-1111-2222"));
        userRepository.save(new User("박프론트", "010-9876-5432"));
        userRepository.save(new User("최백", "010-7777-7777"));

        userRepository.findAll().forEach(user -> log.info("Preloaded User: " + user));
    }

}
