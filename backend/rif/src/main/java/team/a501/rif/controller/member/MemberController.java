package team.a501.rif.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.a501.rif.domain.member.Member;

import team.a501.rif.dto.member.MemberRegisterRequest;
import team.a501.rif.service.member.MemberService;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/member")
    public ResponseEntity<?> registerMember(@RequestBody MemberRegisterRequest memberRegister) {

        Member member = memberService.register(memberRegister);
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }
    @PostMapping(value = "/api/hello")
    public String hello(){
        log.info("info ={}","hello");
        return "hello";
    }
}
