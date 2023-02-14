package team.a501.rif;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import team.a501.rif.dto.achievement.AchievementSaveRequest;
import team.a501.rif.dto.badge.BadgeSaveRequest;
import team.a501.rif.dto.member.MemberRegisterRequest;
import team.a501.rif.repository.achievement.AchievementRepository;
import team.a501.rif.repository.badge.BadgeRepository;
import team.a501.rif.repository.member.MemberRepository;
import team.a501.rif.service.achievement.AchievementService;
import team.a501.rif.service.badge.BadgeService;
import team.a501.rif.service.member.MemberService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class DummyDataInitializerTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private AchievementService achievementService;
    @Autowired
    private AchievementRepository achievementRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BadgeService badgeService;

    @Autowired
    private BadgeRepository badgeRepository;

    @DisplayName("json parser test")
    @Test
    void jsonParserTest() throws IOException {

        File membersJson = new ClassPathResource("members.json").getFile();
        File achievementsJson = new ClassPathResource("achievements.json").getFile();
        File badgesJson = new ClassPathResource("badges.json").getFile();

        ObjectMapper objectMapper = new ObjectMapper();

        List<MemberRegisterRequest> memberRegisterRequests
                = objectMapper.readValue(membersJson, new TypeReference<>() {
        });

        memberService.registerAll(memberRegisterRequests);

        List<AchievementSaveRequest> achievementSaveRequests
                = objectMapper.readValue(achievementsJson, new TypeReference<>() {
        });

        achievementService.saveAll(achievementSaveRequests);

        List<BadgeSaveRequest> badgeSaveRequests
                = objectMapper.readValue(badgesJson, new TypeReference<>() {
        });

        badgeService.saveAll(badgeSaveRequests);


        Assertions.assertThat(memberRepository.count()).isEqualTo(57l);

        Assertions.assertThat(badgeRepository.count()).isEqualTo(30l);

        Assertions.assertThat(achievementRepository.count()).isEqualTo(9l);
    }
}