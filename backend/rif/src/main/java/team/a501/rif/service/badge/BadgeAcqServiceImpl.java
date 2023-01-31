package team.a501.rif.service.badge;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.domain.badge.BadgeAcq;
import team.a501.rif.domain.member.Member;
import team.a501.rif.repository.badge.BadgeAcqRepository;
import team.a501.rif.repository.badge.BadgeRepository;
import team.a501.rif.repository.member.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class BadgeAcqServiceImpl implements BadgeAcqService{

    private final BadgeAcqRepository badgeAcqRepository;
    private final MemberRepository memberRepository;
    private final BadgeRepository badgeRepository;

    @Override
    @Transactional
    public BadgeAcq save(String memberId, Long badgeId) {


        Member member = memberRepository
                .findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("인자로 넘어온 memberId를 갖는 member를 찾을 수 없습니다"));

//        tempMember.getBadgeAcqs().containsKey(badgeId) 중복되는 뱃지를 가지고 있다

        Badge badge = badgeRepository
                .findById(badgeId)
                .orElseThrow(() -> new NoSuchElementException("인자로 넘어온 badgeI를 갖는 badge를 찾을 수 없습니다"));

        BadgeAcq badgeAcq = badgeAcqRepository.save(new BadgeAcq());

        badge.addBadgeAcq(badgeAcq);
        member.addBadgeAcq(badgeAcq);

        return badgeAcq;
    }

    @Override
    @Transactional
    public List<BadgeAcq> findByMemberUid(String memberUid){

        Member member = memberRepository
                .findByUid(memberUid)
                .orElseThrow(() -> new NoSuchElementException("인자로 넘어온 studentId를 갖는 멤버가 없습니다"));

        return badgeAcqRepository.findByMember(member);
    }
}
