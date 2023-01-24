package team.a501.rif.repository.badge;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import team.a501.rif.domain.badge.Badge;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class) // JUnit4의 @Runwith(SpringRunner.class)를 대체한다
@DataJpaTest(showSql = false) // 기본값은 true이다. application.yml의 logging 설정과 중복되어 콘솔에 쿼리가 두 번 출력되는 것을 방지한다
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // application.yml에 설정한 data source를 사용한다
@Rollback(false) // 연결되어 있는 DB에 실제로 쿼리를 반영한다. 기본값은 true이다
class BadgeRepositoryTest {

    @Autowired
    private BadgeRepository badgeRepository;

    @BeforeEach
    void testSetUp(){

        Badge badge1 = Badge.builder().tier(1)
                .description("뱃지 1")
                .badgeImgPath("badge/1.png")
                .build();

        Badge badge2 = Badge.builder().tier(2)
                .description("뱃지 2")
                .badgeImgPath("badge/2.png")
                .build();

        Badge badge3 = Badge.builder().tier(3)
                .description("뱃지 3")
                .badgeImgPath("badge/3.png")
                .build();

        badgeRepository.save(badge1);
        badgeRepository.save(badge2);
        badgeRepository.save(badge3);
    }

    @AfterEach
    void cleanUp(){

        badgeRepository.deleteAll();
    }

    @DisplayName("뱃지 추가하기")
    @Test
    void createBadge(){

        Badge badge = Badge.builder()
                .tier(1)
                .description("뱃지를 추가합니다")
                .badgeImgPath("badge/badge.png")
                .build();

        badgeRepository.save(badge); // DataJpaTest는 실제로 해당 엔티티를 DB에 저장하지는 않는다. 테스트가 종료될 때 트랜잭션을 모두 롤백한다
        assertThat(badge.getId()).isEqualTo(4L);
    }

    @DisplayName("모든 뱃지 조회")
    @Test
    void findBadge(){

        List<Badge> all = badgeRepository.findAll();
        System.out.println("==============================================");
        for(var badge: all){
            System.out.println(badge);
        }
        System.out.println("==============================================");

        assertThat(all.size()).isEqualTo(3);
    }


}