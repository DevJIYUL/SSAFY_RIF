package team.a501.rif.controller.member;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.a501.rif.Repository.member.MemberRepository;
import team.a501.rif.domain.member.Member;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping
public class MemberController {

    private final MemberRepository memberRepository;

    @PostMapping("/member")
    public ResponseEntity<?> registerMember(@RequestBody HashMap<String,Object> member){
        System.out.println(member);

//        Member.builder()
//                .id(member.get("id"))
//
//        Member x = memberRepository.save(member);
//        log.info("info log : {}",x);
        return new ResponseEntity<Map>(member,HttpStatus.OK);
    }

}
