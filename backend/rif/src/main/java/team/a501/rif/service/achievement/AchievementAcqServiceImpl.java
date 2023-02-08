package team.a501.rif.service.achievement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.a501.rif.domain.achievement.Achievement;
import team.a501.rif.domain.achievement.AchievementAcq;
import team.a501.rif.domain.member.Member;
import team.a501.rif.dto.achievement.AchievementAcqInfo;
import team.a501.rif.repository.achievement.AchievementAcqRepository;
import team.a501.rif.repository.member.MemberRepository;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class AchievementAcqServiceImpl implements AchievementAcqService {

    private final AchievementAcqRepository achievementAcqRepository;
    private final AchievementService achievementService;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public AchievementAcqInfo save(String memberId, Long achievementId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException());

        Achievement achievement = achievementService.findById(achievementId);

        AchievementAcq achievementAcq = achievementAcqRepository.save(new AchievementAcq());
        achievement.addAchievementAcq(achievementAcq);
        member.addAchievementAcq(achievementAcq);

        return AchievementAcqInfo.from(achievementAcq);
    }
}
