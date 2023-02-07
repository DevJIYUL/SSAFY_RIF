package team.a501.rif.service.badge;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.domain.badge.BadgeAcq;
import team.a501.rif.domain.member.Member;
import team.a501.rif.dto.badge.BadgeAcqInfo;
import team.a501.rif.exception.ExceptionCode;
import team.a501.rif.exception.RifCustomException;
import team.a501.rif.repository.badge.BadgeAcqRepository;
import team.a501.rif.repository.badge.BadgeRepository;
import team.a501.rif.repository.member.MemberRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class BadgeAcqServiceImpl implements BadgeAcqService {

    private final BadgeAcqRepository badgeAcqRepository;
    private final BadgeRepository badgeRepository;
    private final MemberRepository memberRepository;

    @Override
    public BadgeAcqInfo save(String memberId, Long badgeId) {


        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RifCustomException(ExceptionCode.ENTITY_INSTANCE_NOT_FOUND));

        Badge badge = badgeRepository
                .findById(badgeId)
                .orElseThrow(() -> new RifCustomException(ExceptionCode.ENTITY_INSTANCE_NOT_FOUND));

        BadgeAcq badgeAcq = badgeAcqRepository.save(new BadgeAcq());
        badge.addBadgeAcq(badgeAcq);
        member.addBadgeAcq(badgeAcq);

        return BadgeAcqInfo.from(badgeAcq);
    }

    @Override
    public void delete(BadgeAcq badgeAcq) {
        badgeAcqRepository.delete(badgeAcq);
    }


}
