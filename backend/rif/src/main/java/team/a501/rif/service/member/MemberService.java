package team.a501.rif.service.member;

import org.springframework.security.core.userdetails.UserDetailsService;
import team.a501.rif.domain.member.Member;

import team.a501.rif.dto.badge.BadgeAcqInfo;
import team.a501.rif.dto.member.MemberRegisterRequest;

import java.util.List;


public interface MemberService extends UserDetailsService {

    Member register(MemberRegisterRequest memberRegister);

    Member findByUid(String uid);

    Member findById(String id);

    List<BadgeAcqInfo> findAllBadgeAcq(String memberId);
    void updateDisplayingBadges(String memberId, List<Long> toDisplay);
    void deleteByUid(String uid);

    void deleteById(String id);
}
