package team.a501.rif.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.a501.rif.domain.member.Member;
import team.a501.rif.repository.achievement.AchievementAcqRepository;
import team.a501.rif.repository.achievement.AchievementRepository;
import team.a501.rif.repository.badge.BadgeAcqRepository;
import team.a501.rif.repository.badge.BadgeRepository;
import team.a501.rif.repository.member.MemberRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final AchievementAcqRepository achievementAcqRepository;
    private final AchievementRepository achievementRepository;
    private final BadgeAcqRepository badgeAcqRepository;
    private final BadgeRepository badgeRepository;

    @Transactional
    public void save(Member entity){
        memberRepository.save(entity);
    }

    @Transactional
    public void deleteByStudentId(String studentId){

        Optional<Member> byId = memberRepository.findByStudentId(studentId);
        Member member = byId.orElseThrow();

        // Badge.badgeAcqs에서 삭제
        for(var acq: member.getBadgeAcqs().values()){
            acq.getBadge().removeBadgeAcq(acq);
        }

        // Achievement.achievementAcqs에서 삭제
        for(var acq: member.getAchievementAcqs().values()){
            acq.getAchievement().removeAchievementAcq(acq);
        }

        memberRepository.delete(member);
    }
}
