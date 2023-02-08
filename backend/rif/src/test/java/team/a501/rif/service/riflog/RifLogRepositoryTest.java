package team.a501.rif.service.riflog;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import team.a501.rif.domain.member.Member;
import team.a501.rif.domain.riflog.RifLog;
import team.a501.rif.dto.riflog.RifLogInfo;
import team.a501.rif.dto.riflog.RifLogSaveRequest;
import team.a501.rif.repository.member.MemberRepository;
import team.a501.rif.repository.riflog.RifLogRepository;

@SpringBootTest
class RifLogRepositoryTest {

    @Autowired
    private RifLogService rifLogService;
    @Autowired
    private RifLogRepository rifLogRepository;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("Slice Test")
    @Test
    void rifLogSliceTest() {
        Member member = memberRepository.save(Member.builder()
                .id("0847836")
                .password("0847836")
                .uid("uid")
                .name("kang")
                .point(1000)
                .exp(10)
                .profileImgPath("/profile/default")
                .build());

        for (int i = 0; i < 9; ++i) {
            rifLogService.save(RifLogSaveRequest.builder()
                    .uid("uid")
                    .plasticTotal(1)
                    .plasticOk(1)
                    .recycleTotal(2)
                    .recycleOk(2)
                    .build());
        }

        PageRequest pageable = PageRequest.of(1, 7);
        Slice<RifLog> memberRifLogSlice = rifLogRepository.findByMember(member, pageable);

        Slice<RifLogInfo> memberRifLogInfoSlice = memberRifLogSlice.map(RifLogInfo::from);

        System.out.println(memberRifLogInfoSlice.getContent());
    }
}