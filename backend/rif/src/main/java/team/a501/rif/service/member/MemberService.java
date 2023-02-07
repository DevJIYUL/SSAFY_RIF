package team.a501.rif.service.member;

import org.springframework.security.core.userdetails.UserDetailsService;

import team.a501.rif.dto.achievement.AchievementAcqInfo;
import team.a501.rif.dto.badge.BadgeAcqInfo;
import team.a501.rif.dto.member.BadgeGatchaResponse;
import team.a501.rif.dto.member.MemberRegisterRequest;
import team.a501.rif.dto.member.MemberResponse;

import java.util.List;


public interface MemberService extends UserDetailsService {

    MemberResponse register(MemberRegisterRequest memberRegister);

    void registerAll(List<MemberRegisterRequest> memberRegisterRequests);

    MemberResponse findByUid(String uid);

    MemberResponse findById(String id);

    List<BadgeAcqInfo> findAllBadgeAcq(String memberId);

    List<AchievementAcqInfo> findAllAchievementAcq(String memberId);

    List<AchievementAcqInfo> findAchievementAcqDisplaying(String memberId);

    BadgeGatchaResponse drawRandomBadge(String memberId);
    BadgeAcqInfo updateBadgeDisplaying(String memberId, Long badgeId);

    AchievementAcqInfo updateAchievementDisplaying(String memberId, Long achievementId);
    void deleteByUid(String uid);

    void deleteById(String id);
}
