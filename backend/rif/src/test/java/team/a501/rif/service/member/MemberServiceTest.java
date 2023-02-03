package team.a501.rif.service.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import team.a501.rif.domain.achievement.Achievement;
import team.a501.rif.domain.achievement.AchievementAcq;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.domain.badge.BadgeAcq;
import team.a501.rif.domain.member.Member;
import team.a501.rif.dto.member.BadgeGatchaResponse;
import team.a501.rif.dto.member.MemberRegisterRequest;
import team.a501.rif.dto.member.MemberResponse;
import team.a501.rif.repository.achievement.AchievementAcqRepository;
import team.a501.rif.repository.achievement.AchievementRepository;
import team.a501.rif.repository.badge.BadgeAcqRepository;
import team.a501.rif.repository.badge.BadgeRepository;
import team.a501.rif.repository.member.MemberRepository;
import team.a501.rif.service.member.MemberService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"command.line.runner.enabled=true"})
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("멤버 등록")
    @Test
    void saveMemberTest() {
        MemberResponse memberResponse = memberService.register(MemberRegisterRequest.builder()
                .id("12345")
                .password("12345")
                .uid("abcdef")
                .name("saveMemberTest")
                .build());

        System.out.println(memberResponse);

        assertThat(memberRepository.count()).isEqualTo(5);
    }

    @DisplayName("뱃지 가챠")
    @Test
    void getRandomBadge() {
        memberService.register(MemberRegisterRequest.builder()
                .id("12345")
                .password("12345")
                .uid("abcdef")
                .name("getRandomBadgeTest")
                .build());

        Member member = memberRepository.findById("12345")
                .orElseThrow(() -> new NoSuchElementException());

        member.setPoint(10000);

        BadgeGatchaResponse badgeGatchaResponse = memberService.drawRandomBadge(member.getId());

        assertThat(member.getBadgeAcqs().size()).isEqualTo(1);

        System.out.println(badgeGatchaResponse);
    }
}