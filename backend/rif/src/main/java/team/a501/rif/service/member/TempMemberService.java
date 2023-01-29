package team.a501.rif.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.a501.rif.domain.badge.BadgeAcq;
import team.a501.rif.domain.tmp.TempMember;
import team.a501.rif.repository.achievement.AchievementAcqRepository;
import team.a501.rif.repository.achievement.AchievementRepository;
import team.a501.rif.repository.badge.BadgeAcqRepository;
import team.a501.rif.repository.badge.BadgeRepository;
import team.a501.rif.repository.tmp.TempMemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TempMemberService {

    private final TempMemberRepository tempMemberRepository;
    private final AchievementAcqRepository achievementAcqRepository;
    private final AchievementRepository achievementRepository;
    private final BadgeAcqRepository badgeAcqRepository;
    private final BadgeRepository badgeRepository;

    @Transactional
    public void save(TempMember entity){
        tempMemberRepository.save(entity);
    }

    @Transactional
    public void deleteByStudentId(String studentId){

        Optional<TempMember> byId = tempMemberRepository.findByStudentId(studentId);
        TempMember tempMember = byId.orElseThrow();

        // Badge.badgeAcqs에서 삭제
        for(var acq: tempMember.getBadgeAcqs().values()){
            acq.getBadge().removeBadgeAcq(acq);
        }

        // Achievement.achievementAcqs에서 삭제
        for(var acq: tempMember.getAchievementAcqs().values()){
            acq.getAchievement().removeAchievementAcq(acq);
        }

        tempMemberRepository.delete(tempMember);
    }
}
