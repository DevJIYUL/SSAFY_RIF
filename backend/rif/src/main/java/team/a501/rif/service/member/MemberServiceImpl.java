package team.a501.rif.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import team.a501.rif.domain.badge.Badge;
import team.a501.rif.domain.badge.BadgeAcq;
import team.a501.rif.domain.member.Member;
import team.a501.rif.dto.badge.BadgeAcqInfo;
import team.a501.rif.dto.badge.BadgeInfo;
import team.a501.rif.dto.member.MemberRegisterRequest;
import team.a501.rif.repository.member.MemberRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Member register(MemberRegisterRequest memberRegister) {
        return memberRepository.save(Member.builder()
                .id(memberRegister.getId())
                .password(passwordEncoder.encode(memberRegister.getPassword()))
                .uid(memberRegister.getUid())
                .name(memberRegister.getName())
                .point(0)
                .exp(0)
                .profileImgPath(Member.DEFAULT_PROFILE_IMG)
                .build());
    }

    @Override
    public void registerAll(List<MemberRegisterRequest> memberRegisterRequests) {
        for(var e: memberRegisterRequests){
            memberRepository.save(Member.builder()
                    .id(e.getId())
                    .password(passwordEncoder.encode(e.getPassword()))
                    .uid(e.getUid())
                    .name(e.getName())
                    .point(0)
                    .exp(0)
                    .profileImgPath(Member.DEFAULT_PROFILE_IMG)
                    .build());
            memberRepository.flush();
        }
    }

    @Override
    @Transactional
    public Member findByUid(String uid) {
        return memberRepository
                .findByUid(uid)
                .orElseThrow(() -> new NoSuchElementException("해당하는 Uid로 멤버를 찾을 수 없습니다"));
    }

    @Override
    @Transactional
    public Member findById(String id) {
        return memberRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당하는 Id로 멤버를 찾을 수 없습니다"));
    }

    @Override
    @Transactional
    public List<BadgeAcqInfo> findAllBadgeAcq(String memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException());

        List<BadgeAcqInfo> badgeAcqInfoList = member.getBadgeAcqs()
                .values()
                .stream()
                .map(badgeAcq -> {

                    Badge badge = badgeAcq.getBadge();

                    BadgeInfo badgeInfo = BadgeInfo.builder()
                            .id(badge.getId())
                            .title(badge.getTitle())
                            .tier(badge.getTier())
                            .description(badge.getDescription())
                            .imgPath(badge.getImgPath())
                            .build();

                    return BadgeAcqInfo.builder()
                            .id(badgeAcq.getId())
                            .onDisplay(badgeAcq.getOnDisplay())
                            .achievedAt(badgeAcq.getCreated())
                            .badgeInfo(badgeInfo)
                            .build();
                })
                .collect(Collectors.toList());

        return badgeAcqInfoList;
    }

    @Override
    @Transactional
    public void updateDisplayingBadges(String memberId, List<Long> badgeIds) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException());

        for (var badgeAcq : member.getBadgeAcqs().values()) {

            badgeAcq.setOnDisplay(false);
        }

        for (var badgeId : badgeIds) {

            BadgeAcq badgeAcq = Optional
                    .ofNullable(member.getBadgeAcqs().get(badgeId))
                    .orElseThrow(() -> new NoSuchElementException());

            badgeAcq.setOnDisplay(true);
        }
    }

    @Override
    @Transactional
    public void deleteByUid(String uid) {

        Member member = memberRepository
                .findByUid(uid)
                .orElseThrow(() -> new NoSuchElementException("인자로 넘어온 uid에 해당하는 레코드를 찾을 수 없습니다"));

        // Badge.badgeAcqs에서 삭제
        for (var acq : member.getBadgeAcqs().values()) {
            acq.getBadge().removeBadgeAcq(acq);
        }

        // Achievement.achievementAcqs에서 삭제
        for (var acq : member.getAchievementAcqs().values()) {
            acq.getAchievement().removeAchievementAcq(acq);
        }

        memberRepository.delete(member);
    }

    @Override
    @Transactional
    public void deleteById(String id) {

        Member member = memberRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 Id로 멤버를 찾을 수 없습니다"));

        for (var acq : member.getBadgeAcqs().values()) {
            acq.getBadge().removeBadgeAcq(acq);
        }

        for (var acq : member.getAchievementAcqs().values()) {
            acq.getAchievement().removeAchievementAcq(acq);
        }

        memberRepository.delete(member);

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails = memberRepository
                .findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 username으로 멤버를 조회할 수 없습니다"));

        return userDetails;
    }
}
