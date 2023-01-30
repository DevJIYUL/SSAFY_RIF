package team.a501.rif.repository.badge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.domain.badge.BadgeAcq;
import team.a501.rif.domain.member.Member;
import team.a501.rif.repository.member.MemberRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BadgeAcqRepositoryTest {

    @Autowired
    private BadgeAcqRepository badgeAcqRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    @BeforeEach
    void setUp() {
        Member member = Member.builder()
                .id(UUID.randomUUID().toString())
                .studentId("0847836")
                .password("0847836")
                .name("강승곤")
                .point(0)
                .exp(0)
                .profileImgPath("/profile/default.png")
                .build();

        memberRepository.save(member);

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
    }

    @DisplayName("뱃지 획득 추가 및 Auditing 확인")
    @Test
    void createBadgeAcq() {

        String studentId = "0847836";
        Optional<Member> byStudentId = memberRepository.findByStudentId(studentId);
        Member member = byStudentId.orElseThrow();

        System.out.println("=================================================================");
        System.out.println("member = " + member);
        System.out.println("=================================================================");

        List<Badge> all1 = badgeRepository.findAll();
        if(all1.isEmpty()) throw new NoSuchElementException();
        Badge badge = all1.get(0);

        System.out.println("=================================================================");
        System.out.println("badge = " + badge);
        System.out.println("=================================================================");

        BadgeAcq badgeAcq = new BadgeAcq();
        badgeAcq.setOnDisplay(false);

        badge.addBadgeAcq(badgeAcq); // 지연 로딩 발생
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
}