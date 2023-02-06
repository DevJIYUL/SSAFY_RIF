package team.a501.rif.service.badge;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.domain.badge.BadgeAcq;
import team.a501.rif.domain.member.Member;
import team.a501.rif.exception.ExceptionCode;
import team.a501.rif.exception.RifCustomException;
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
    private final BadgeRepository badgeRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public BadgeAcq save(String memberId, Long badgeId) {


        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RifCustomException(ExceptionCode.ENTITY_INSTANCE_NOT_FOUND));

//        tempMember.getBadgeAcqs().containsKey(badgeId) 중복되는 뱃지를 가지고 있다

        Badge badge = badgeRepository
                .findById(badgeId)
                .orElseThrow(() -> new RifCustomException(ExceptionCode.ENTITY_INSTANCE_NOT_FOUND));

        BadgeAcq badgeAcq = badgeAcqRepository.save(new BadgeAcq());

        badge.addBadgeAcq(badgeAcq);
        member.addBadgeAcq(badgeAcq);

        return badgeAcq;
    }

    @Override
    @Transactional
    public List<BadgeAcq> findByMemberId(String memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RifCustomException(ExceptionCode.ENTITY_INSTANCE_NOT_FOUND));

        return badgeAcqRepository.findByMember(member);
    }

    @Override
    @Transactional
    public List<BadgeAcq> findByMemberUid(String memberUid){

        Member member = memberRepository.findByUid(memberUid)
                .orElseThrow(() -> new RifCustomException(ExceptionCode.ENTITY_INSTANCE_NOT_FOUND));

        return badgeAcqRepository.findByMember(member);
    }
}
