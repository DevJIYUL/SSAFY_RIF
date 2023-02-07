package team.a501.rif.service.member;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import team.a501.rif.config.Jwt.JwtAuthenticationFilter;
import team.a501.rif.config.Jwt.JwtTokenProvider;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.domain.badge.BadgeAcq;
import team.a501.rif.domain.member.Member;
import team.a501.rif.dto.badge.BadgeAcqInfo;
import team.a501.rif.dto.badge.BadgeInfo;
import team.a501.rif.dto.member.*;
import team.a501.rif.exception.NotEnoughPoints;
import team.a501.rif.repository.badge.BadgeRepository;
import team.a501.rif.repository.member.MemberRepository;
import team.a501.rif.service.badge.BadgeAcqService;
import team.a501.rif.service.badge.BadgeService;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final BadgeAcqService badgeAcqService;
    private final BadgeRepository badgeRepository;

    private final BadgeService badgeService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    @Transactional
    public MemberResponse register(MemberRegisterRequest memberRegister) {
        Member member = memberRepository.save(Member.builder()
                .id(memberRegister.getId())
                .password(passwordEncoder.encode(memberRegister.getPassword()))
                .uid(memberRegister.getUid())
                .name(memberRegister.getName())
                .point(1000)
                .exp(0)
                .profileImgPath(Member.DEFAULT_PROFILE_IMG)
                .build());

        return MemberResponse.builder()
                .id(member.getId())
                .uid(member.getUid())
                .name(member.getName())
                .imgPath(member.getProfileImgPath())
                .build();
    }

    @Override
    public void registerAll(List<MemberRegisterRequest> memberRegisterRequests) {
        for (var e : memberRegisterRequests) {
            memberRepository.save(Member.builder()
                    .id(e.getId())
                    .password(passwordEncoder.encode(e.getPassword()))
                    .uid(e.getUid())
                    .name(e.getName())
                    .point(1000)
                    .exp(0)
                    .profileImgPath(Member.DEFAULT_PROFILE_IMG)
                    .build());
            memberRepository.flush();
        }
    }

    @Override
    @Transactional
    public MemberResponse findByUid(String uid) {
        Member member = memberRepository
                .findByUid(uid)
                .orElseThrow(() -> new NoSuchElementException("해당하는 Uid로 멤버를 찾을 수 없습니다"));

        return MemberResponse.builder()
                .id(member.getId())
                .uid(member.getUid())
                .name(member.getName())
                .imgPath(member.getProfileImgPath())
                .build();
    }

    @Override
    @Transactional
    public MemberResponse findById(String id) {
        Member member = memberRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당하는 Id로 멤버를 찾을 수 없습니다"));

        return MemberResponse.builder()
                .id(member.getId())
                .uid(member.getUid())
                .name(member.getName())
                .imgPath(member.getProfileImgPath())
                .build();
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

    private static final Integer GATCHA_COST = 100;

    @Override
    public BadgeGatchaResponse drawRandomBadge(String memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException());

        Integer balance = member.getPoint();
        if (balance < GATCHA_COST)
            throw new NotEnoughPoints();

        member.setPoint(balance - GATCHA_COST);

        // get random badge
        Badge badge = badgeService.getRandomBadge();

        // if we already have the badge
        Boolean reduplicated = member.hasBadge(badge.getId());

        // or add badgeAcq to member
        if (!reduplicated) {
            badgeAcqService.save(memberId, badge.getId());
        }

        return BadgeGatchaResponse.builder()
                .reduplicated(reduplicated)
                .remainingPoint(member.getPoint())
                .badge(badge.getInfo())
                .build();
    }

    @Override
    @Transactional
    public BadgeAcqInfo updateDisplayingBadge(String memberId, Long badgeId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException());

        BadgeAcq badgeAcq = Optional.of(member.getBadgeAcqs().get(badgeId))
                .orElseThrow(() -> new NoSuchElementException());

        badgeAcq.toggleOnDisplay();

        return badgeAcq.getInfo();
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
    public MemberResponse passwordChange(HttpServletRequest request, String memberId, PasswordChangeRequest passwordChangeRequest) {
        String accessToken = jwtAuthenticationFilter.resolveToken(request);

        log.info("passwordChange info : {}",passwordChangeRequest,memberId);
        log.info("memberid ={}",memberId);
        log.info("accesstoken info ={}",accessToken);
        Claims claims = jwtTokenProvider.parseClaims(accessToken);
        log.info("Claims info = {}",claims);
        Member member = memberRepository.findById(claims.getSubject()).orElseThrow(()->new UsernameNotFoundException("해당 유저를 찾을수 없습니다."));
        log.info("Member by token info = {}",member);
        if(!memberId.equals(member.getId()))throw new BadCredentialsException("잘못된 유저입니다.");
        if(!passwordEncoder.matches(passwordChangeRequest.getCurrentPassword(),member.getPassword())) throw new BadCredentialsException("다시 입력해주세요.");
        if(!passwordChangeRequest.getNewPassword().equals(passwordChangeRequest.getNewPasswordConfirm())) throw new BadCredentialsException("다시 입력해주세요.");
        Member changeMember = memberRepository.save(Member.builder()
                .id(member.getId())
                .uid(member.getUid())
                .exp(member.getExp())
                .password(passwordEncoder.encode(passwordChangeRequest.getNewPassword()))
                .name(member.getName())
                .point(member.getPoint())
                .profileImgPath(member.getProfileImgPath())
                .build());
        return MemberResponse.builder()
                .id(changeMember.getId())
                .uid(changeMember.getUid())
                .name(changeMember.getName())
                .imgPath(changeMember.getProfileImgPath())
                .build();
    }

    @Override
    public List<GetMembersName> getMembersName() {
        List<Member> getNameAll = memberRepository.findAll();
        List<GetMembersName> response = new ArrayList<>();
        for (Member b : getNameAll){
            response.add(GetMembersName.builder().name(b.getName()).build());
        }
        return response;
    }

    @Override
    public List<FindMemberByName> findByName(String name) {
        List<Member> repo = memberRepository.findAllByName(name);
        List<FindMemberByName> response = new ArrayList<>();
        for (Member b : repo){
            response.add(FindMemberByName.builder()
                    .id(b.getId())
                    .name(b.getName())
                    .exp(b.getExp())
                    .profileImgPath(b.getProfileImgPath())
                    .build());
        }
        return response;
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
