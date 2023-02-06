package team.a501.rif.controller.member;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.a501.rif.domain.member.Member;
import team.a501.rif.dto.badge.BadgeAcqInfo;
import team.a501.rif.dto.member.BadgeGatchaResponse;
import team.a501.rif.dto.member.MemberBadgeAcqInfoResponse;
import team.a501.rif.dto.member.MemberRegisterRequest;
import team.a501.rif.dto.member.PasswordChangeRequest;
import team.a501.rif.dto.member.MemberResponse;
import team.a501.rif.service.member.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<MemberResponse> registerMember(@RequestBody MemberRegisterRequest memberRegister) {

        MemberResponse memberResponse = memberService.register(memberRegister);
        return ResponseEntity.ok(memberResponse);
    }

    @GetMapping("/member")
    public ResponseEntity<MemberResponse> findMemberByUid(@RequestParam String uid){

        MemberResponse memberResponse = memberService.findByUid(uid);
        return ResponseEntity.ok(memberResponse);
    }

    @GetMapping("/member/profile")
    public ResponseEntity<MemberResponse> findMemberById(@RequestParam String id){
        MemberResponse memberResponse = memberService.findById(id);
        return ResponseEntity.ok(memberResponse);
    }

    @GetMapping("/api/member/badge")
    public ResponseEntity<MemberBadgeAcqInfoResponse> getAllMemberBadgeAcq(@RequestParam String memberId) {

        List<BadgeAcqInfo> badgeAcqInfoList = memberService.findAllBadgeAcq(memberId);

        MemberBadgeAcqInfoResponse response = MemberBadgeAcqInfoResponse.builder()
                .badgeAcqInfoList(badgeAcqInfoList)
                .build();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/api/member/badge")
    public ResponseEntity<BadgeAcqInfo> updateDisplayingBadge(@RequestParam String memberId,
                                                              @RequestParam Long badgeId) {

        BadgeAcqInfo response = memberService.updateDisplayingBadge(memberId, badgeId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/gatcha")
    public ResponseEntity<BadgeGatchaResponse> badgeGatcha(@RequestParam String memberId) {

        BadgeGatchaResponse badgeGatchaResponse = memberService.drawRandomBadge(memberId);

        return ResponseEntity.ok(badgeGatchaResponse);
    }
    @PostMapping(value = "/api/hello")
    public String hello(){
        log.info("info ={}","hello");
        return "hello";
    }
    @PatchMapping(value = "/api/member/password{memberId}")
    public ResponseEntity<MemberResponse> passwordChange(HttpServletRequest request,
                                 @PathVariable("memberId") String memberId,
                                 @RequestBody PasswordChangeRequest passwordChangeRequest) throws Exception{

        MemberResponse member = memberService.passwordChange(request,memberId,passwordChangeRequest);
        return ResponseEntity.ok(member);
    }
}
