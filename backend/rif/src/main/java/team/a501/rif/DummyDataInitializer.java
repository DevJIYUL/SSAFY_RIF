package team.a501.rif;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import team.a501.rif.dto.achievement.AchievementSaveRequest;
import team.a501.rif.dto.badge.BadgeSaveRequest;
import team.a501.rif.dto.member.MemberRegisterRequest;
import team.a501.rif.dto.riflog.RifLogSaveRequest;
import team.a501.rif.service.achievement.AchievementService;
import team.a501.rif.service.badge.BadgeService;
import team.a501.rif.service.member.MemberService;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
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
    private final BadgeService badgeService;
    private final AchievementService achievementService;

    @Override
    @Transactional
    public void run(String... args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        InputStream membersJsonInputStream = new ClassPathResource("members.json").getInputStream();
        InputStream achievementsJsonInputStream = new ClassPathResource("achievements.json").getInputStream();
        InputStream badgesJsonInputStream = new ClassPathResource("badges.json").getInputStream();
        InputStream rifLogsJsonInputStream = new ClassPathResource("riflogs.json").getInputStream();

        File membersJson = File.createTempFile("tmp-members", ".json");
        File achievementsJson = File.createTempFile("tmp-achievements", ".json");
        File badgesJson = File.createTempFile("tmp-badges", ".json");
        File rifLogsJson = File.createTempFile("tmp-riflogs", ".json");

        Files.copy(membersJsonInputStream, membersJson.toPath());
        Files.copy(achievementsJsonInputStream, achievementsJson.toPath());
        Files.copy(badgesJsonInputStream, badgesJson.toPath());
        Files.copy(rifLogsJsonInputStream, rifLogsJson.toPath());

        // 멤버 등록
        List<MemberRegisterRequest> memberRegisterRequestList =
                objectMapper.readValue(membersJson, new TypeReference<>() {
                });
        memberService.registerAll(memberRegisterRequestList);

        // 뱃지 등록
        List<BadgeSaveRequest> badgeSaveRequestList =
                objectMapper.readValue(badgesJson, new TypeReference<>() {
                });
        badgeService.saveAll(badgeSaveRequestList);

        // 업적 등록
        List<AchievementSaveRequest> achievementSaveRequests =
                objectMapper.readValue(achievementsJson, new TypeReference<>() {
                });
        achievementService.saveAll(achievementSaveRequests);

        // RifLog 추가
        List<RifLogSaveRequest> rifLogSaveRequests =
                objectMapper.readValue(rifLogsJson, new TypeReference<>() {});
        for (var r :
                rifLogSaveRequests) {
            memberService.addRifLog(r);
        }

        // 멤버별 5번씩 갓차
        for (var m :
                memberRegisterRequestList) {
            memberService.drawRandomBadge(m.getId());
            memberService.drawRandomBadge(m.getId());
            memberService.drawRandomBadge(m.getId());
            memberService.drawRandomBadge(m.getId());
            memberService.drawRandomBadge(m.getId());
        }
    }
}
