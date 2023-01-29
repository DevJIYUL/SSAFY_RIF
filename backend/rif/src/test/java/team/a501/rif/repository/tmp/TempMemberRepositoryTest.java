package team.a501.rif.repository.tmp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import team.a501.rif.domain.achievement.Achievement;
import team.a501.rif.domain.achievement.AchievementAcq;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.domain.badge.BadgeAcq;
import team.a501.rif.domain.tmp.TempMember;
import team.a501.rif.repository.achievement.AchievementAcqRepository;
import team.a501.rif.repository.achievement.AchievementRepository;
import team.a501.rif.repository.badge.BadgeAcqRepository;
import team.a501.rif.repository.badge.BadgeRepository;

import java.util.UUID;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TempMemberRepositoryTest {
    @Autowired
    private TempMemberRepository tempMemberRepository;

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private AchievementAcqRepository achievementAcqRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private BadgeAcqRepository badgeAcqRepository;

    @BeforeEach
    void setUp(){

        // 테스트용 멤버
        String id = UUID.randomUUID().toString();
        String studentId = "0844269";
        String password = studentId;
        String name = "송지율";
        Integer point = 0;
        Integer exp = 0;
        String profileImgPath = "/profile/default.png";

        TempMember tempMember = TempMember.builder()
                .id(id)
                .studentId(studentId)
                .password(password)
                .name(name)
                .point(point)
                .exp(exp)
                .profileImgPath(profileImgPath)
                .build();
        tempMemberRepository.save(tempMember);

        // 테스트용 뱃지, 업적
        Badge badge = Badge.builder().tier(1)
                .description("뱃지 1")
                .badgeImgPath("badge/1.png")
                .build();
        badgeRepository.save(badge);

        Achievement achievement = Achievement.builder()
                .tier(0)
                .title("업적 1")
                .description("Lorem ipsum dolor sit amet")
                .achievementImgPath("/achievement/1.png")
                .build();
        achievementRepository.save(achievement);

        // 테스트용 뱃지 획득, 업적획득
        BadgeAcq badgeAcq = new BadgeAcq();
        badgeAcq.setOnDisplay(false);
        badgeAcqRepository.save(badgeAcq);

        AchievementAcq achievementAcq = new AchievementAcq();
        achievementAcq.setOnDisplay(false);
        achievementAcqRepository.save(achievementAcq);

        badge.addBadgeAcq(badgeAcq);
        tempMember.addBadgeAcq(badgeAcq);

        achievement.addAchievementAcq(achievementAcq);
        tempMember.addAchievementAcq(achievementAcq);
    }

    @DisplayName("임시 멤버 추가하기")
    @Test
    void createTempMember(){

        String id = UUID.randomUUID().toString(); // todo 테스트 용
        String studentId = "0847836";
        String password = studentId;
        String name = "강승곤";
        Integer point = 0;
        Integer exp = 0;
        String profileImgPath = "/profile/default.png";

        TempMember tempMember = TempMember.builder()
                .id(id)
                .studentId(studentId)
                .password(password)
                .name(name)
                .point(point)
                .exp(exp)
                .profileImgPath(profileImgPath)
                .build();
        tempMemberRepository.save(tempMember);
    }
}