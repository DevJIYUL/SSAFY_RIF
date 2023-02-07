package team.a501.rif.service.member;

import org.springframework.security.core.userdetails.UserDetailsService;
import team.a501.rif.dto.achievement.AchievementAcqInfo;
import team.a501.rif.dto.badge.BadgeAcqInfo;
import team.a501.rif.dto.member.*;
import team.a501.rif.dto.riflog.RifLogInfo;
import team.a501.rif.dto.riflog.RifLogSaveRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface MemberService extends UserDetailsService {

    MemberResponse register(MemberRegisterRequest dto);

    void registerAll(List<MemberRegisterRequest> dtoList);

    MemberResponse findByUid(String uid);

    MemberResponse findById(String id);

    List<BadgeAcqInfo> findAllBadgeAcq(String memberId);

    List<BadgeAcqInfo> findBadgeAcqOnDisplay(String memberId);

    BadgeAcqInfo updateBadgeOnDisplay(String memberId, Long badgeId);

    BadgeGatchaResponse drawRandomBadge(String memberId);

    List<AchievementAcqInfo> findAllAchievementAcq(String memberId);

    List<AchievementAcqInfo> findAchievementAcqOnDisplay(String memberId);

    AchievementAcqInfo updateAchievementOnDisplay(String memberId, Long achievementId);

    BadgeAcqInfo addBadgeAcq(String memberId, Long badgeId);

    AchievementAcqInfo addAchievementAcq(String memberId, Long achievementId);

    RifLogInfo addRifLog(RifLogSaveRequest dto);

    void deleteByUid(String uid);

    void deleteById(String id);

    MemberResponse passwordChange(HttpServletRequest request, String memberId, PasswordChangeRequest passwordChangeRequest);

    List<GetMembersName> getMembersName();

    List<FindMemberByName> findByName(String name);
}
