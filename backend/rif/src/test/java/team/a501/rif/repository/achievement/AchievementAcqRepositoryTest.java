package team.a501.rif.repository.achievement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import team.a501.rif.domain.achievement.Achievement;
import team.a501.rif.domain.achievement.AchievementAcq;
import team.a501.rif.domain.tmp.TempMember;
import team.a501.rif.repository.tmp.TempMemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AchievementAcqRepositoryTest {

    @Autowired
    private AchievementAcqRepository achievementAcqRepository;

    @Autowired
    private TempMemberRepository tempMemberRepository;

    @Autowired
    private AchievementRepository achievementRepository;

    @BeforeEach
    void setUp(){
        TempMember tempMember = TempMember.builder()
                .id(UUID.randomUUID().toString())
                .studentId("0847836")
                .password("0847836")
                .name("강승곤")
                .point(0)
                .exp(0)
                .profileImgPath("/profile/default.png")
                .build();

        tempMemberRepository.save(tempMember);

        Achievement achievement = Achievement.builder()
                .tier(0)
                .title("업적 1")
                .description("Tempus imperdiet nulla malesuada pellentesque elit eget gravida")
                .achievementImgPath("/achievement/1.png")
                .build();

        achievementRepository.save(achievement);
    }

    @DisplayName("업적 획득 추가")
    @Test
    void createAchievementAcq(){

        Optional<TempMember> byStudentId = tempMemberRepository.findByStudentId("0847836");
        TempMember tempMember = byStudentId.orElseThrow();

        Optional<Achievement> byId = achievementRepository.findById(1L);
        Achievement achievement = byId.orElseThrow();

        AchievementAcq achievementAcq = new AchievementAcq();
        achievementAcq.setOnDisplay(false);

        achievementAcqRepository.save(achievementAcq); // 영속성 컨텍스트에서 manage 됨

        achievement.addAchievementAcq(achievementAcq);
        // tempMemeber가 Badge/Achievement Acq 객체를 저장할 때 원본 Badge, Achievement의 Id를 참조하므로
        // achievement에 먼저 acq를 등록해야한다
        tempMember.addAchievementAcq(achievementAcq);

        List<AchievementAcq> all = achievementAcqRepository.findAll();
        System.out.println("==========================================================");
        for(var e: all){
            System.out.println(e);
        }
        System.out.println("==========================================================");
    }

    @AfterEach
    void cleanUp(){
        achievementAcqRepository.deleteAll();
        achievementRepository.deleteAll();
        tempMemberRepository.deleteAll();
    }
}