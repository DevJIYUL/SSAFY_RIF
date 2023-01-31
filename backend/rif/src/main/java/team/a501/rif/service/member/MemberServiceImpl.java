package team.a501.rif.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import team.a501.rif.domain.member.Member;
import team.a501.rif.dto.member.MemberRegister;
import team.a501.rif.repository.member.MemberRepository;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Member register(MemberRegister memberRegister) {
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails = memberRepository
                .findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 username으로 멤버를 조회할 수 없습니다"));

        return userDetails;
    }
}
