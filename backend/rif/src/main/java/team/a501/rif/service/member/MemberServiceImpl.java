package team.a501.rif.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.a501.rif.Repository.member.MemberRepository;
import team.a501.rif.domain.member.Member;

import java.util.ArrayList;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j

public class MemberServiceImpl implements MemberService  {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Member registeMember(Member member) {
        log.info("registeMember service info={}",member);
        Member encodedMember = Member.builder()
                .id(member.getId())
                .studentId(member.getStudentId())
                .name(member.getName())
                .password(passwordEncoder.encode(member.getPassword()))
                .exp(member.getExp())
                .point(member.getPoint())
                .profileImgPath(member.getProfileImgPath())
                .build();
        log.info("encodedMember service info={}",encodedMember);
        return memberRepository.save(encodedMember);
    }

    @Override
    public UserDetails loadUserByUsername(String studentId) throws UsernameNotFoundException {
        Member member = memberRepository.findByStudentId(studentId);
        log.info("loadUserByUsername info = {}",member);
        return Member.builder()
                .id(member.getId())
                .studentId(member.getStudentId())
                .password(member.getPassword())
                .build();
    }
}
