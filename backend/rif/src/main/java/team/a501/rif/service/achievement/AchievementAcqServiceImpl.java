package team.a501.rif.service.achievement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.a501.rif.domain.achievement.AchievementAcq;
import team.a501.rif.repository.achievement.AchievementAcqRepository;
import team.a501.rif.repository.achievement.AchievementRepository;
import team.a501.rif.repository.member.MemberRepository;
import team.a501.rif.service.member.MemberService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AchievementAcqServiceImpl implements AchievementAcqService{

    private final MemberService memberService;
    private final AchievementAcqRepository achievementAcqRepository;
    private final AchievementRepository achievementRepository;

    @Override
    public AchievementAcq save(String memeberId, String achievementId) {
        return null;
    }

    @Override
    public List<AchievementAcq> findByMemberId(String memberId) {
        return null;
    }

    @Override
    public List<AchievementAcq> findByMemberUid(String memberUid) {
        return null;
    }
}
