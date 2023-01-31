package team.a501.rif.service.member;

import org.springframework.security.core.userdetails.UserDetailsService;
import team.a501.rif.domain.member.Member;

public interface MemberService extends UserDetailsService {
    Member registeMember(Member member);
}
