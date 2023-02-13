package team.a501.rif.service.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.a501.rif.dto.member.MemberRankingResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @DisplayName("랭킹 Top10 경험치, 학번 사전순 정렬")
    @Test
    void getTop10(){
        List<MemberRankingResponse> top10 = memberService.getFirst10ByOrderByExp();

        for (var e :
                top10) {
            System.out.println(e);
        }
    }

    @DisplayName("랭킹 Top10, 나의 랭킹 확인")
    @Test
    void getTop10WithMyRanking(){

        List<MemberRankingResponse> top10WithMyRanking = memberService.getFirstAllByOrderByExp("0847836");

        for (var e :
                top10WithMyRanking) {

            System.out.println(e);
        }
    }

}