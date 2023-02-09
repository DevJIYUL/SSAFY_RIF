package team.a501.rif.repository.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import team.a501.rif.domain.member.Member;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("임시 멤버 추가하기")
    @Test
    void createMember(){

        memberRepository.save(Member.builder()
                .id("0847836")
                .password("0847836")
                .uid(UUID.randomUUID().toString())
                .name("kang")
                .point(1000)
                .exp(10)
                .profileImgPath("/profile/default")
                .build());

        memberRepository.save(Member.builder()
                .id("1234567")
                .password("1234567")
                .uid(UUID.randomUUID().toString())
                .name("kim")
                .point(2000)
                .exp(20)
                .profileImgPath("/profile.default")
                .build());

        List<Member> top10 = memberRepository.findFirst1010ByOrderByExpDesc();

        for(var e: top10){
            System.out.println(e);
        }

        assertThat(top10.size()).isEqualTo(2);
    }
}