package team.a501.rif.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import team.a501.rif.domain.achievement.Achievement;
import team.a501.rif.domain.achievement.AchievementAcq;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.domain.badge.BadgeAcq;
import team.a501.rif.domain.tmp.TempMember;
import team.a501.rif.repository.achievement.AchievementAcqRepository;
import team.a501.rif.repository.achievement.AchievementRepository;
import team.a501.rif.repository.badge.BadgeAcqRepository;
import team.a501.rif.repository.badge.BadgeRepository;
import team.a501.rif.repository.tmp.TempMemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TempMemberServiceTest {

    @Autowired
    private TempMemberService tempMemberService;
    @Autowired
    private TempMemberRepository tempMemberRepository;
    @Autowired
    private AchievementAcqRepository achievementAcqRepository;
    @Autowired
    private AchievementRepository achievementRepository;
    @Autowired
    private BadgeAcqRepository badgeAcqRepository;
    @Autowired
    private BadgeRepository badgeRepository;

    @BeforeEach
    void setUp() {

        String id = UUID.randomUUID().toString();
        String studentId = "0847836";
        String password = "0847836";
        String name = "강승곤";
        Integer point = 0;
        Integer exp = 0;
        String profileImgPath = "/profile/default.png";

        TempMember tempMember = tempMemberRepository.save(TempMember.builder()
                .id(id)
                .studentId(studentId)
                .password(password)
                .name(name)
                .point(point)
                .exp(exp)
                .profileImgPath(profileImgPath)
                .build());

        Achievement achievement = achievementRepository.save(Achievement.builder()
                .tier(0)
                .title("업적 1")
                .description("Tempus imperdiet nulla malesuada pellentesque elit eget gravida")
                .achievementImgPath("/achievement/1.png")
                .build());

        Badge badge1 = badgeRepository.save(Badge.builder()
                .title("뱃지 1")
                .tier(1)
                .description("Lorem ipsum")
                .badgeImgPath("badge/1.png")
                .build());

        AchievementAcq achievementAcq = achievementAcqRepository.save(new AchievementAcq());
        achievement.addAchievementAcq(achievementAcq);
        tempMember.addAchievementAcq(achievementAcq);

        BadgeAcq badgeAcq = badgeAcqRepository.save(new BadgeAcq());
        badge1.addBadgeAcq(badgeAcq);
        tempMember.addBadgeAcq(badgeAcq);
    }

    @DisplayName("멤버 저장")
    @Test
    void saveMember() {
        String id = UUID.randomUUID().toString();
        String studentId = "0844269";
        String password = "0844269";
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

        tempMemberService.save(tempMember);

        long count = tempMemberRepository.count();
        System.out.println("======================================");
        System.out.println("count = " + count);
        System.out.println("======================================");

        assertThat(count).isEqualTo(2L);
    }

    @DisplayName("멤버 삭제")
    @Test
    @Transactional
    void removeMemberByStudentId() {

        System.out.println("System.out: 업적 개수 = " + achievementRepository.count());
        System.out.println("System.out: 업적 획득 개수 = " + achievementAcqRepository.count());
        System.out.println("System.out: 뱃지 개수 = " + badgeRepository.count());
        System.out.println("System.out: 뱃지 획득 개수 = " + badgeAcqRepository.count());

        tempMemberService.deleteByStudentId("0847836");

        assertThat(tempMemberRepository.count()).isEqualTo(0L);
        assertThat(badgeAcqRepository.count()).isEqualTo(0L);
        assertThat(achievementAcqRepository.count()).isEqualTo(0L);
        assertThat(badgeRepository.count()).isEqualTo(1L);
        assertThat(achievementRepository.count()).isEqualTo(1L);

        List<Achievement> achievementRepositoryAll = achievementRepository.findAll();
        for (var e : achievementRepositoryAll) {
            System.out.println("title = " + e.getTitle());
            System.out.println("total related acqs = " + e.getAchievementAcqs().size());
        }

        List<Badge> badgeRepositoryAll = badgeRepository.findAll();
        for (var e : badgeRepositoryAll) {
            System.out.println("title = " + e.getTitle());
            System.out.println("total related acqs = " + e.getBadgeAcqs().size());
        }
    }
}