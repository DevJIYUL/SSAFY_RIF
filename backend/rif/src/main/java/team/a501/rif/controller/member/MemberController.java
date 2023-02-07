package team.a501.rif.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.a501.rif.dto.achievement.AchievementAcqInfo;
import team.a501.rif.dto.badge.BadgeAcqInfo;
import team.a501.rif.dto.member.BadgeGatchaResponse;
import team.a501.rif.dto.member.MemberRegisterRequest;
import team.a501.rif.dto.member.MemberResponse;
import team.a501.rif.dto.riflog.RifLogInfo;
import team.a501.rif.dto.riflog.RifLogSaveRequest;
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
    public ResponseEntity<MemberResponse> registerMember(@RequestBody MemberRegisterRequest body) {

        MemberResponse memberResponse = memberService.register(body);
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

    @GetMapping("/member/badge")
    public ResponseEntity<List<BadgeAcqInfo>> getBadgeAcqOnDisplay(@RequestParam String memberId){

        List<BadgeAcqInfo> onDisplayBadge = memberService.findBadgeAcqOnDisplay(memberId);

        return ResponseEntity.ok(onDisplayBadge);
    }

    @GetMapping("/v/member/badge")
    public ResponseEntity<List<BadgeAcqInfo>> getAllMemberBadgeAcq(@RequestParam String memberId) {

        List<BadgeAcqInfo> totalBadge = memberService.findAllBadgeAcq(memberId);

        return ResponseEntity.ok(totalBadge);
    }

    @PatchMapping("/v/member/badge")
    public ResponseEntity<BadgeAcqInfo> updateBadgesDisplaying(@RequestParam String memberId,
                                                              @RequestParam Long badgeId) {

        BadgeAcqInfo badge = memberService.updateBadgeOnDisplay(memberId, badgeId);

        return ResponseEntity.ok(badge);
    }

    @GetMapping("/member/achievement")
    public ResponseEntity<List<AchievementAcqInfo>> getAchievementDisplaying(@RequestParam String memberId){

        List<AchievementAcqInfo> onDisplayAchievements = memberService.findAchievementAcqOnDisplay(memberId);

        return ResponseEntity.ok(onDisplayAchievements);
    }

    @GetMapping("/v/member/achievement")
    public ResponseEntity<List<AchievementAcqInfo>> getAllMemberAchievementAcq(@RequestParam String memberId){

        List<AchievementAcqInfo> totalAchievement = memberService.findAllAchievementAcq(memberId);

        return ResponseEntity.ok(totalAchievement);
    }

    @PostMapping("/v/gatcha")
    public ResponseEntity<BadgeGatchaResponse> badgeGatcha(@RequestParam String memberId) {

        BadgeGatchaResponse result = memberService.drawRandomBadge(memberId);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/member/riflog")
    public ResponseEntity<RifLogInfo> addRifLog(@RequestBody RifLogSaveRequest body){

        RifLogInfo rifLogInfo = memberService.addRifLog(body);

        return ResponseEntity.ok(rifLogInfo);
    }
    @PostMapping(value = "/v/hello")
    public String hello(){
        log.info("info ={}","hello");
        return "hello";
    }
}
