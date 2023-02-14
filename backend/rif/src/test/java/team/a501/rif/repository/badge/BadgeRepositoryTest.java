package team.a501.rif.repository.badge;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import team.a501.rif.domain.badge.Badge;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false) // application.yml의 logging 설정과 중복되어 콘솔에 쿼리가 두 번 출력되는 것을 방지한다 (default = true)
class BadgeRepositoryTest {

    @Autowired
    private BadgeRepository badgeRepository;

    @BeforeEach
    void setUp() {

        Badge badge1 = Badge.builder().tier(1)
                .description("뱃지 1")
                .imgPath("badge/1.png")
                .build();

        Badge badge2 = Badge.builder().tier(2)
                .description("뱃지 2")
                .imgPath("badge/2.png")
                .build();

        Badge badge3 = Badge.builder().tier(3)
                .description("뱃지 3")
                .imgPath("badge/3.png")
                .build();

        badgeRepository.save(badge1);
        badgeRepository.save(badge2);
        badgeRepository.save(badge3);
    }

    @AfterEach
    void cleanUp() {
        badgeRepository.deleteAll();
    }

    @DisplayName("뱃지 추가하기")
    @Test
    void createBadge() {

        Badge badge = Badge.builder()
                .tier(1)
                .description("뱃지를 추가합니다")
                .imgPath("badge/badge.png")
                .build();

        badgeRepository.save(badge); // DataJpaTest는 실제로 해당 엔티티를 DB에 저장하지는 않는다. 테스트가 종료될 때 트랜잭션을 모두 롤백한다
        assertThat(badgeRepository.count()).isEqualTo(4L);
    }

    @DisplayName("모든 뱃지 조회")
    @Test
    void findBadge() {

        List<Badge> all = badgeRepository.findAll();
        System.out.println("==============================================");
        for (var badge : all) {
            System.out.println(badge);
        }
        System.out.println("==============================================");

        assertThat(all.size()).isEqualTo(3);
    }

    @DisplayName("전체 뱃지 개수")
    @Test
    void countBadges() {

        Long count = badgeRepository.count();
        assertThat(count).isEqualTo(3L);
    }
}