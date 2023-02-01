package team.a501.rif.service.badge;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.domain.badge.BadgeAcq;
import team.a501.rif.domain.member.Member;
import team.a501.rif.repository.badge.BadgeRepository;
import team.a501.rif.repository.member.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BadgeAcqServiceTest {

    @Autowired
    private BadgeAcqServiceImpl badgeAcqService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BadgeRepository badgeRepository;

    @DisplayName("뱃지 획득 저장하기")
    @Transactional
    @Test
    void saveBadgeAcq() {

        Member member = memberRepository.save(Member.builder()
                .id("1234567")
                .password("1234567")
                .uid(UUID.randomUUID().toString())
                .name("쨩싱콴")
                .point(0)
                .exp(0)
                .profileImgPath("/profile/default.png")
                .build());

        Badge badge = badgeRepository.save(Badge.builder()
                .tier(10)
                .title("어서옵쇼")
                .description("처음 서비스를 이용하였다")
                .badgeImgPath("/badge/greeting.png")
                .build());

        BadgeAcq badgeAcq = badgeAcqService.save(member.getId(), badge.getId());

        assertThat(badgeAcq).isNotNull();
        System.out.println("badgeAcq = " + badgeAcq);
    }

    @DisplayName("멤버별 뱃지 획득 조회하기")
    @Transactional
    @Test
    void findByMember(){

        Member member1 = memberRepository.save(Member.builder()
                .id("1234567")
                .password("1234567")
                .uid(UUID.randomUUID().toString())
                .name("쨩싱콴")
                .point(0)
                .exp(0)
                .profileImgPath("/profile/default.png")
                .build());

        Member member2 = memberRepository.save(Member.builder()
                .id("7654321")
                .password("7654321")
                .uid(UUID.randomUUID().toString())
                .name("강승곤")
                .point(0)
                .exp(0)
                .profileImgPath("/profile/default.png")
                .build());

        Badge badge1 = badgeRepository.save(Badge.builder()
                .tier(10)
                .title("어서옵쇼")
                .description("처음 서비스를 이용하였다")
                .badgeImgPath("/badge/greeting.png")
                .build());

        Badge badge2 = badgeRepository.save(Badge.builder()
                .tier(15)
                .title("우수 멤버")
                .description("쓰레기 분리 배출의 귀재")
                .badgeImgPath("/badge/good-recycler.png")
                .build());

        badgeAcqService.save(member1.getId(), badge1.getId());
        badgeAcqService.save(member1.getId(), badge2.getId());

        badgeAcqService.save(member2.getId(), badge1.getId());

        List<BadgeAcq> byMember = badgeAcqService.findByMemberUid(member1.getUid());

        assertThat(byMember.size()).isEqualTo(2);
        System.out.println("============================================");
        for(var e: byMember){
            System.out.println("e = " + e);
        }
        System.out.println("============================================");

    }

}