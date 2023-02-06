package team.a501.rif.service.member;

import org.springframework.security.core.userdetails.UserDetailsService;
import team.a501.rif.domain.member.Member;

import team.a501.rif.dto.badge.BadgeAcqInfo;
import team.a501.rif.dto.member.BadgeGatchaResponse;
import team.a501.rif.dto.member.MemberRegisterRequest;
import team.a501.rif.dto.member.MemberResponse;
import team.a501.rif.dto.member.PasswordChangeRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface MemberService extends UserDetailsService {

    MemberResponse register(MemberRegisterRequest memberRegister);

    void registerAll(List<MemberRegisterRequest> memberRegisterRequests);

    MemberResponse findByUid(String uid);

    MemberResponse findById(String id);

    List<BadgeAcqInfo> findAllBadgeAcq(String memberId);

    BadgeGatchaResponse drawRandomBadge(String memberId);
    BadgeAcqInfo updateDisplayingBadge(String memberId, Long badgeId);
    void deleteByUid(String uid);

    void deleteById(String id);

    MemberResponse passwordChange(HttpServletRequest request, String memberId, PasswordChangeRequest passwordChangeRequest);
}
