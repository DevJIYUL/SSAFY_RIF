package team.a501.rif.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.a501.rif.domain.member.Member;

import team.a501.rif.dto.member.MemberRegisterRequest;
import team.a501.rif.service.member.MemberService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/member", produces = "application/json")


    public ResponseEntity<?> registerMember(@RequestBody MemberRegisterRequest memberRegister) {

        Member member = memberService.register(memberRegister);
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

}
