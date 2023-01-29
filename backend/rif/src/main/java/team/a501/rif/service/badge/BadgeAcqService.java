package team.a501.rif.service.badge;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.domain.badge.BadgeAcq;
import team.a501.rif.domain.tmp.TempMember;
import team.a501.rif.repository.badge.BadgeAcqRepository;
import team.a501.rif.repository.badge.BadgeRepository;
import team.a501.rif.repository.tmp.TempMemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class BadgeAcqService {

    private final BadgeAcqRepository badgeAcqRepository;
    private final TempMemberRepository tempMemberRepository;
    private final BadgeRepository badgeRepository;

    @Transactional
    public BadgeAcq save(String memberId, Long badgeId) {


        TempMember tempMember = tempMemberRepository
                .findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("인자로 넘어온 memberId를 갖는 member를 찾을 수 없습니다"));

//        tempMember.getBadgeAcqs().containsKey(badgeId) 중복되는 뱃지를 가지고 있다

        Badge badge = badgeRepository
                .findById(badgeId)
                .orElseThrow(() -> new NoSuchElementException("인자로 넘어온 badgeI를 갖는 badge를 찾을 수 없습니다"));

        BadgeAcq badgeAcq = badgeAcqRepository.save(new BadgeAcq());

        badge.addBadgeAcq(badgeAcq);
        tempMember.addBadgeAcq(badgeAcq);

        return badgeAcq;
    }

    @Transactional
    public List<BadgeAcq> findByMemberStudentId(String studentId){

        TempMember tempMember = tempMemberRepository
                .findByStudentId(studentId)
                .orElseThrow(() -> new NoSuchElementException("인자로 넘어온 studentId를 갖는 멤버가 없습니다"));

        return badgeAcqRepository.findByMember(tempMember);
    }
}
