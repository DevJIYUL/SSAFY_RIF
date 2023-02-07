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
import java.util.ArrayList;
import java.util.List;

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
                .title("업적1")
                .tier(1)
                .description("이것은 업적1 입니다")
                .achievementImgPath("/achievement/1.png")
                .build());

        Achievement achievement2 = achievementRepository.save(Achievement.builder()
                .title("업적2")
                .tier(2)
                .description("이것은 업적2 입니다")
                .achievementImgPath("/achievement/2.png")
                .build());

        Achievement achievement3 = achievementRepository.save(Achievement.builder()
                .title("업적3")
                .tier(3)
                .description("이것은 업적3 입니다")
                .achievementImgPath("/achievement/3.png")
                .build());

        Achievement achievement4 = achievementRepository.save(Achievement.builder()
                .title("업적4")
                .tier(4)
                .description("이것은 업적4 입니다")
                .achievementImgPath("/achievement/4.png")
                .build());

        memberService.addBadgeAcq(registerForm.getId(), badge1.getId());
        memberService.addBadgeAcq(registerForm.getId(), badge2.getId());
        memberService.addBadgeAcq(registerForm.getId(), badge3.getId());
        memberService.addBadgeAcq(registerForm.getId(), badge4.getId());

        memberService.updateBadgeOnDisplay(registerForm.getId(), badge1.getId());
        memberService.updateBadgeOnDisplay(registerForm.getId(), badge2.getId());

        memberService.addAchievementAcq(registerForm.getId(), achievement1.getId());
        memberService.addAchievementAcq(registerForm.getId(), achievement2.getId());
        memberService.addAchievementAcq(registerForm.getId(), achievement3.getId());
        memberService.addAchievementAcq(registerForm.getId(), achievement4.getId());

        memberService.updateAchievementOnDisplay(registerForm.getId(), achievement1.getId());
        memberService.updateAchievementOnDisplay(registerForm.getId(), achievement2.getId());
    }
}
