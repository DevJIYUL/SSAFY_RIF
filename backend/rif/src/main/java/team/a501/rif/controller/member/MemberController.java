package team.a501.rif.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import team.a501.rif.domain.member.Member;
import team.a501.rif.dto.badge.BadgeAcqInfo;
import team.a501.rif.dto.member.BadgeGatchaResponse;
import team.a501.rif.dto.member.MemberBadgeAcqInfoResponse;
import team.a501.rif.dto.member.MemberRegisterRequest;
import team.a501.rif.dto.member.MemberResponse;
import team.a501.rif.service.member.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/member")
    public ResponseEntity<MemberResponse> registerMember(@RequestBody MemberRegisterRequest memberRegister) {

        MemberResponse memberResponse = memberService.register(memberRegister);
        return ResponseEntity.ok(memberResponse);
    }

    @GetMapping(value = "/member")
    public ResponseEntity<MemberResponse> findMember(@RequestParam String uid){

        MemberResponse memberResponse = memberService.findByUid(uid);
        return ResponseEntity.ok(memberResponse);
    }

    @GetMapping("/api/member/badge")
    public ResponseEntity<MemberBadgeAcqInfoResponse> getAllMemberBadgeAcq(Authentication auth) {

        List<BadgeAcqInfo> badgeAcqInfoList = memberService.findAllBadgeAcq(auth.getName());

        MemberBadgeAcqInfoResponse response = MemberBadgeAcqInfoResponse.builder()
                .badgeAcqInfoList(badgeAcqInfoList)
                .build();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/api/member/badge")
    public ResponseEntity<BadgeAcqInfo> updateDisplayingBadge(Authentication auth,
                                                              @RequestParam Long id) {

        BadgeAcqInfo response = memberService.updateDisplayingBadge(auth.getName(), id);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/gatcha")
    public ResponseEntity<BadgeGatchaResponse> badgeGatcha(Authentication auth) {

        String memberId = auth.getName();

        BadgeGatchaResponse badgeGatchaResponse = memberService.drawRandomBadge(memberId);

        return ResponseEntity.ok(badgeGatchaResponse);
    }

}
