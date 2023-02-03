package team.a501.rif.service.achievement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.a501.rif.domain.achievement.Achievement;
import team.a501.rif.domain.achievement.AchievementAcq;
import team.a501.rif.domain.member.Member;
import team.a501.rif.dto.achievement.AchievementAcqResponse;
import team.a501.rif.dto.achievement.AchievementSaveRequest;
import team.a501.rif.dto.member.MemberRegisterRequest;
import team.a501.rif.service.member.MemberService;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AchievementAcqServiceTest {

    @Autowired private AchievementAcqService achievementAcqService;

    @Autowired private MemberService memberService;
    @Autowired private AchievementService achievementService;

//    @DisplayName("대표 업적 불러오기")
//    @Transactional
//    @Test
//    void findOnDisplayedAchievementAcq(){
//
//        // 멤버, 업적 등록
//        Member member = memberService.register(MemberRegisterRequest.builder()
//                .id("1234567")
//                .password("some_password")
//                .uid("this_is_my_uid")
//                .name("강승곤")
//                .build());
//
//        Achievement achievement = achievementService.save(AchievementSaveRequest
//                .builder()
//                .tier(100)
//                .title("업적1")
//                .description("이것은 업적1 입니다")
//                .achievementImgPath("/achievement/default.png")
//                .build());
//
//        AchievementAcq achievementAcq = achievementAcqService.create(member.getId(), achievement.getId());
//        achievementAcq.setOnDisplay(true);
//
//        List<AchievementAcqResponse> response = achievementAcqService.findOnDisplayedItemsOfMember(member.getId());
//
//        System.out.println(response);
//
//        assertThat(response.size()).isEqualTo(1L);
//
//    }

}