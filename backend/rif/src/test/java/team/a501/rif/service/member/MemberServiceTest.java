package team.a501.rif.service.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.a501.rif.dto.achievement.AchievementAcqInfo;
import team.a501.rif.dto.riflog.RifLogSaveRequest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @DisplayName("업적 달성 테스트")
    @Test
    @Transactional
    void achievementAcquiringTest() {

        memberService.addRifLog(RifLogSaveRequest.builder()
                .uid("67f18755")
                .plasticTotal(1)
                .plasticOk(1)
                .recycleTotal(0)
                .recycleOk(0)
                .build());

        List<AchievementAcqInfo> achievementAcqInfoList = memberService.findAllAchievementAcq("0847647");

        List<AchievementAcqInfo> achievedAdded = achievementAcqInfoList
                .stream()
                .filter(info -> info.getHasAchievement())
                .collect(Collectors.toList());

        assertThat(achievedAdded.size()).isEqualTo(2);

        for (var e :
                achievementAcqInfoList) {
            System.out.println(e);
        }
    }
}