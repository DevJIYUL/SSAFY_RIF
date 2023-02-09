package team.a501.rif;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import team.a501.rif.domain.achievement.Achievement;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.dto.member.MemberRegisterRequest;
import team.a501.rif.repository.achievement.AchievementRepository;
import team.a501.rif.repository.badge.BadgeRepository;
import team.a501.rif.service.member.MemberService;

import javax.transaction.Transactional;

@ConditionalOnProperty(
        prefix = "command.line.runner",
        value = "enabled",
        havingValue = "true",
        matchIfMissing = true)
@RequiredArgsConstructor
@Component
@Profile("dev")
public class DummyDataInitializer implements CommandLineRunner {

    private final MemberService memberService;
    private final BadgeRepository badgeRepository;
    private final AchievementRepository achievementRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // todo member 추가하지

        MemberRegisterRequest registerForm = MemberRegisterRequest
                .builder()
                .id("0847647")
                .password("0847647")
                .uid("67f18755")
                .name("진윤태")
                .build();
        memberService.register(registerForm);

        Badge badge1 = badgeRepository.save(Badge.builder()
                .title("뱃지1")
                .tier(1)
                .description("이것은 뱃지1 입니다")
                .badgeImgPath("/badge/1.png")
                .build());

        Badge badge2 = badgeRepository.save(Badge.builder()
                .title("뱃지2")
                .tier(2)
                .description("이것은 뱃지2 입니다")
                .badgeImgPath("/badge/2.png")
                .build());

        Badge badge3 = badgeRepository.save(Badge.builder()
                .title("뱃지3")
                .tier(3)
                .description("이것은 뱃지3 입니다")
                .badgeImgPath("/badge/3.png")
                .build());

        Badge badge4 = badgeRepository.save(Badge.builder()
                .title("뱃지4")
                .tier(4)
                .description("이것은 뱃지4 입니다")
                .badgeImgPath("/badge/4.png")
                .build());

        // todo achievement 추가하기
        Achievement achievement1 = achievementRepository.save(Achievement.builder()
                .title("Welcome!")
                .tier(4)
                .description("반갑습니다 :)")
                .achievementImgPath("/achievement/1.png")
                .achievementTypeOrdinal(0)
                .build());

        Achievement achievement2 = achievementRepository.save(Achievement.builder()
                .title("First Plastic Success")
                .tier(3)
                .description("첫번째 플라스틱 분류를 성공하였습니다")
                .achievementImgPath("/achievement/2.png")
                .achievementTypeOrdinal(1)
                .build());

        Achievement achievement3 = achievementRepository.save(Achievement.builder()
                .title("First Recycle Success")
                .tier(2)
                .description("첫번째 재활용 분류를 성공하였습니다")
                .achievementImgPath("/achievement/3.png")
                .achievementTypeOrdinal(4)
                .build());

        Achievement achievement4 = achievementRepository.save(Achievement.builder()
                .title("First RIF Perfect")
                .tier(1)
                .description("완벽히 분류를 해내셨습니다!")
                .achievementImgPath("/achievement/4.png")
                .achievementTypeOrdinal(7)
                .build());

        memberService.addBadgeAcq(registerForm.getId(), badge1.getId());
        memberService.addBadgeAcq(registerForm.getId(), badge2.getId());
        memberService.addBadgeAcq(registerForm.getId(), badge3.getId());
        memberService.addBadgeAcq(registerForm.getId(), badge4.getId());

        memberService.updateBadgeOnDisplay(registerForm.getId(), badge1.getId());
        memberService.updateBadgeOnDisplay(registerForm.getId(), badge2.getId());
    }
}
