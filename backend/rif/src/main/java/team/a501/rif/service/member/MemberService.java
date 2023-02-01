package team.a501.rif.service.member;

import org.springframework.security.core.userdetails.UserDetailsService;
import team.a501.rif.domain.member.Member;

import team.a501.rif.dto.member.MemberRegister;


public interface MemberService extends UserDetailsService {

    Member register(MemberRegister memberRegister);
    void deleteByUid(String uid);
}
