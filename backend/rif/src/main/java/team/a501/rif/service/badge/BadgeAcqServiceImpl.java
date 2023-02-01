package team.a501.rif.service.badge;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.domain.badge.BadgeAcq;
import team.a501.rif.domain.member.Member;
import team.a501.rif.exception.NoSuchEntityException;
import team.a501.rif.repository.badge.BadgeAcqRepository;
import team.a501.rif.repository.badge.BadgeRepository;
import team.a501.rif.service.member.MemberService;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BadgeAcqServiceImpl implements BadgeAcqService{

    private final BadgeAcqRepository badgeAcqRepository;
    private final BadgeRepository badgeRepository;
    private final MemberService memberService;

    @Override
    @Transactional
    public BadgeAcq save(String memberId, Long badgeId) {


        Member member = memberService.findById(memberId);

//        tempMember.getBadgeAcqs().containsKey(badgeId) 중복되는 뱃지를 가지고 있다

        Badge badge = badgeRepository
                .findById(badgeId)
                .orElseThrow(() -> new NoSuchEntityException(Badge.class.getName()));

        BadgeAcq badgeAcq = badgeAcqRepository.save(new BadgeAcq());

        badge.addBadgeAcq(badgeAcq);
        member.addBadgeAcq(badgeAcq);

        return badgeAcq;
    }

    @Override
    @Transactional
    public List<BadgeAcq> findByMemberId(String memberId) {

        Member member = memberService.findById(memberId);

        return badgeAcqRepository.findByMember(member);
    }

    @Override
    @Transactional
    public List<BadgeAcq> findByMemberUid(String memberUid){

        Member member = memberService.findByUid(memberUid);

        return badgeAcqRepository.findByMember(member);
    }
}
