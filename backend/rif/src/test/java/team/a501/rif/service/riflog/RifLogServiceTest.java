package team.a501.rif.service.riflog;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import team.a501.rif.domain.member.Member;
import team.a501.rif.domain.riflog.RifLog;
import team.a501.rif.dto.member.MemberRegisterRequest;
import team.a501.rif.dto.riflog.RifLogSaveRequest;
import team.a501.rif.repository.riflog.RifLogRepository;
import team.a501.rif.service.member.MemberService;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class RifLogServiceTest {

    @Autowired
    private RifLogService rifLogService;

    @Autowired
    private RifLogRepository rifLogRepository;

    @Autowired
    private MemberService memberService;


    @DisplayName("RifLog를 생성한다")
    @Test
    @Transactional
    void createRifLogTest(){

        RifLogSaveRequest request = RifLogSaveRequest.builder()
                .memberUid("uiduiduid")
                .plasticTotal(10)
                .plasticOk(5)
                .recycleTotal(10)
                .recycleOk(1)
                .adviseIgnored(true)
                .build();

        Member member = memberService.register(MemberRegisterRequest.builder()
                .id("12345")
                .password("12345")
                .uid(request.getMemberUid())
                .name("강승곤")
                .build());

        RifLog rifLog = rifLogService.createRifLog(request);

        System.out.println(rifLog);

        Assertions.assertThat(rifLogRepository.count()).isEqualTo(1);
    }
}