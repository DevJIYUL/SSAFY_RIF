package team.a501.rif.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.a501.rif.dto.achievement.AchievementAcqInfo;
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
@RequestMapping("/api")
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

    @GetMapping("/v/member/badge")
    public ResponseEntity<MemberBadgeAcqInfoResponse> getAllMemberBadgeAcq(@RequestParam String memberId) {

        List<BadgeAcqInfo> badgeAcqInfoList = memberService.findAllBadgeAcq(memberId);

        MemberBadgeAcqInfoResponse response = MemberBadgeAcqInfoResponse.builder()
                .badgeAcqInfoList(badgeAcqInfoList)
                .build();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/v/member/badge")
    public ResponseEntity<BadgeAcqInfo> updateBadgesDisplaying(@RequestParam String memberId,
                                                              @RequestParam Long badgeId) {

        BadgeAcqInfo response = memberService.updateBadgeDisplaying(memberId, badgeId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/member/achievement")
    public ResponseEntity<List<AchievementAcqInfo>> getAchievementDisplaying(@RequestParam String memberId){

        List<AchievementAcqInfo> onDisplayAchievements = memberService.findAchievementAcqDisplaying(memberId);

        return ResponseEntity.ok(onDisplayAchievements);
    }

    @PostMapping("/v/gatcha")
    public ResponseEntity<BadgeGatchaResponse> badgeGatcha(@RequestParam String memberId) {

        BadgeGatchaResponse badgeGatchaResponse = memberService.drawRandomBadge(memberId);

        return ResponseEntity.ok(badgeGatchaResponse);
    }
    @PostMapping(value = "/v/hello")
    public String hello(){
        log.info("info ={}","hello");
        return "hello";
    }
}
