package team.a501.rif.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import team.a501.rif.domain.member.Member;
import team.a501.rif.dto.badge.BadgeAcqInfo;
import team.a501.rif.dto.member.DisplayingBadgeUpdateRequest;
import team.a501.rif.dto.member.MemberBadgeAcqInfoResponse;
import team.a501.rif.dto.member.MemberRegisterRequest;
import team.a501.rif.service.member.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/member")
    public ResponseEntity<Member> registerMember(@RequestBody MemberRegisterRequest memberRegister) {

        Member member = memberService.register(memberRegister);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/api/user/badge")
    public ResponseEntity<MemberBadgeAcqInfoResponse> getAllMemberBadgeAcq(Authentication auth) {

        List<BadgeAcqInfo> badgeAcqInfoList = memberService.findAllBadgeAcq(auth.getName());

        MemberBadgeAcqInfoResponse response = MemberBadgeAcqInfoResponse.builder()
                .badgeAcqInfoList(badgeAcqInfoList)
                .build();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/api/user/badge")
    public ResponseEntity<?> updateDisplayingBadges(Authentication auth,
                                                    @RequestBody DisplayingBadgeUpdateRequest requestBody) {

        memberService.updateDisplayingBadges(
                auth.getName(),
                requestBody.getToDisplay()
        );

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
