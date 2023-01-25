package team.a501.rif.repository.badge;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.domain.badge.BadgeAcq;
import team.a501.rif.domain.tmp.TempMember;
import team.a501.rif.repository.tmp.TempMemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class BadgeAcqRepositoryTest {

    @Autowired
    private BadgeAcqRepository badgeAcqRepository;

    @Autowired
    private TempMemberRepository tempMemberRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    @BeforeEach
    @Transactional
    void setUp() {
        // Member 추가
        TempMember member = TempMember.builder()
                .id(UUID.randomUUID().toString())
                .studentId("0847836")
                .password("0847836")
                .name("강승곤")
                .point(0)
                .exp(0)
                .profileImgPath("/profile/default.png")
                .build();

        tempMemberRepository.save(member);

        // Badge 추가
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

        BadgeAcq badgeAcq = new BadgeAcq();
        badgeAcq.setOnDisplay(false);

        badge1.addBadgeAcq(badgeAcq); // 뱃지
        member.addBadgeAcq(badgeAcq); // 멤버

        badgeAcqRepository.save(badgeAcq); // 뱃지 획득
    }

    @AfterEach
    void cleanUp() {

//        badgeAcqRepository.deleteAll();
//        badgeRepository.deleteAll();
//        tempMemberRepository.deleteAll();
    }

    @DisplayName("뱃지 획득 추가 및 Auditing 확인")
    @Test
    @Transactional
    void createBadgeAcq() {

        String studentId = "0847836";
        Optional<TempMember> byStudentId = tempMemberRepository.findByStudentId(studentId);
        TempMember member = byStudentId.orElseThrow();

        System.out.println("=================================================================");
        System.out.println("member = " + member);
        System.out.println("=================================================================");

        Optional<Badge> byId = badgeRepository.findById(1L);
        Badge badge = byId.orElseThrow();

        System.out.println("=================================================================");
        System.out.println("badge = " + badge);
        System.out.println("=================================================================");

        BadgeAcq badgeAcq = new BadgeAcq();
        badgeAcq.setOnDisplay(false);

        badge.addBadgeAcq(badgeAcq);
        member.addBadgeAcq(badgeAcq);

        badgeAcqRepository.save(badgeAcq);

        List<BadgeAcq> all = badgeAcqRepository.findAll();
        System.out.println("=================================================================");
        for (var acq : all) {
            System.out.println(acq);
            System.out.println(acq.getMember().getClass());
            System.out.println(acq.getBadge().getClass());
        }
        System.out.println("=================================================================");
    }

    @DisplayName("뱃지 획득 조회")
    @Test
    void findAllBadgeAcqById(){

        Optional<BadgeAcq> byId = badgeAcqRepository.findById(4L);
        BadgeAcq badgeAcq = byId.orElseThrow();

        System.out.println("=================================================================");
        System.out.println(badgeAcq.getMember().getClass());
        System.out.println(badgeAcq.getBadge().getClass());
        System.out.println("=================================================================");

    }
}