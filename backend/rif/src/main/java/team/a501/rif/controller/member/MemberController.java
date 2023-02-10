package team.a501.rif.controller.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.a501.rif.dto.achievement.AchievementAcqInfo;
import team.a501.rif.dto.badge.BadgeAcqInfo;
import team.a501.rif.dto.member.*;
import team.a501.rif.dto.riflog.RifLogInfo;
import team.a501.rif.dto.riflog.RifLogSaveRequest;
import team.a501.rif.dto.riflog.RifLogSaveResponse;
import team.a501.rif.service.member.MemberService;
import team.a501.rif.service.riflog.RifLogService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Tag(name = "MemberController")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class MemberController {

    private final MemberService memberService;

    private final RifLogService rifLogService;

    @PostMapping("/member")
    @Operation(summary = "멤버를 등록한다")
    public ResponseEntity<MemberResponse> registerMember(@RequestBody MemberRegisterRequest request) {

        log.info("registerMember request: {}", request);

        MemberResponse memberResponse = memberService.register(request);

        return ResponseEntity.ok(memberResponse);
    }

    @GetMapping("/member")
    @Operation(summary = "UID로 멤버를 조회한다")
    public ResponseEntity<MemberResponse> findMemberByUid(@RequestParam String uid) {
        log.info("findMemberByUid request: {}", uid);

        MemberResponse memberResponse = memberService.findByUid(uid);

        return ResponseEntity.ok(memberResponse);
    }

    @GetMapping("/member/profile")
    @Operation(summary = "ID로 멤버를 조회한다")
    public ResponseEntity<MemberResponse> findMemberById(@RequestParam String id) {
        log.info("findMemberById request: {}", id);

        MemberResponse memberResponse = memberService.findById(id);

        return ResponseEntity.ok(memberResponse);
    }

    @GetMapping("/member/badge")
    @Operation(summary = "멤버의 대표 뱃지를 조회한다")
    public ResponseEntity<Map<String, Object>> findBadgeAcqsOnDisplay(@RequestParam String memberId) {
        log.info("findBadgeAcqsOnDisplay request: {}", memberId);

        List<BadgeAcqInfo> onDisplayBadge = memberService.findBadgeAcqOnDisplay(memberId);

        return ResponseEntity.ok(Map.of("onDisplayBadge", onDisplayBadge));
    }

    @GetMapping("/v/member/badge")
    @Operation(summary = "멤버의 전체 뱃지를 조회한다", description = "인증된 사용자가 자신의 전체 뱃지를 조회할 수 있다")
    public ResponseEntity<Map<String, Object>> findAllMemberBadgeAcq(@RequestParam String memberId) {

        log.info("findAllMemberBadgeAcq: {}", memberId);

        List<BadgeAcqInfo> totalBadge = memberService.findAllBadgeAcq(memberId);

        return ResponseEntity.ok(Map.of("totalBadge", totalBadge));
    }

    @PatchMapping("/v/member/badge")
    @Operation(summary = "멤버의 대표 뱃지를 설정한다", description = "해당 뱃지의 OnDisplay 속성을 토글한다")
    public ResponseEntity<BadgeAcqInfo> updateBadgeOnDisplay(@RequestParam String memberId,
                                                             @RequestParam Long badgeId) {
        log.info("updateBadgeOnDisplay: {}, {}", memberId, badgeId);

        BadgeAcqInfo badge = memberService.updateBadgeOnDisplay(memberId, badgeId);

        return ResponseEntity.ok(badge);
    }

    @GetMapping("/member/achievement")
    @Operation(summary = "멤버의 대표 업적을 조회한다")
    public ResponseEntity<Map<String, Object>> findAchievementAcqsOnDisplay(@RequestParam String memberId) {

        log.info("findAchievementAcqsOnDisplay: {}", memberId);

        List<AchievementAcqInfo> onDisplayAchievements = memberService.findAchievementAcqOnDisplay(memberId);

        return ResponseEntity.ok(Map.of("onDisplayAchievement", onDisplayAchievements));
    }

    @GetMapping("/v/member/achievement")
    @Operation(summary = "멤버의 전체 업적을 조회한다", description = "인증된 사용자가 자신의 전체 업적을 조회할 수 있다")
    public ResponseEntity<Map<String, Object>> findAllMemberAchievementAcq(@RequestParam String memberId) {

        log.info("findAllMemberAchievementAcq: {}", memberId);

        List<AchievementAcqInfo> allAchievementAcq = memberService.findAllAchievementAcq(memberId);

        return ResponseEntity.ok(Map.of("totalAchievement", allAchievementAcq));
    }

    @PatchMapping("/v/member/achievement")
    @Operation(summary = "멤버의 대표 업적을 설정한다", description = "해당 업적의 OnDisplay 속성을 토글한다")
    public ResponseEntity<AchievementAcqInfo> updateAchievementOnDisplay(@RequestParam String memberId,
                                                                         @RequestParam Long achievementId) {

        log.info("updateAchievementOnDisplay: {}, {}", memberId, achievementId);

        AchievementAcqInfo achievementAcqInfo = memberService.updateAchievementOnDisplay(memberId, achievementId);

        return ResponseEntity.ok(achievementAcqInfo);
    }

    @PostMapping("/v/gatcha")
    @Operation(summary = "뱃지 뽑기를 진행한다", description = "인증된 사용자가 100포인트를 사용하여 랜덤한 뱃지를 뽑는다. 이미 획득한 뱃지라면 reduplicated키의 값이 true가 된다")
    public ResponseEntity<BadgeGatchaResponse> badgeGatcha(@RequestParam String memberId) {

        log.info("badgeGatcha: {}", memberId);

        BadgeGatchaResponse result = memberService.drawRandomBadge(memberId);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/v/member/riflog")
    @Operation(summary = "멤버의 전체 RIF 사용 이력을 조회한다")
    public ResponseEntity<Slice<RifLogInfo>> findMemberRifLogs(@RequestParam String memberId,
                                                               @RequestParam Integer page,
                                                               @RequestParam Integer size) {

        log.info("findMemberRifLogs: {}, {}, {}", memberId, page, size);

        Pageable pageable = PageRequest.of(page, size);

        Slice<RifLogInfo> rifLogInfoSlice = rifLogService.findByMember(memberId, pageable);

        return ResponseEntity.ok(rifLogInfoSlice);
    }

    @PostMapping("/member/riflog")
    @Operation(summary = "멤버의 RIF 사용 이력을 추가한다")
    public ResponseEntity<RifLogSaveResponse> saveRifLog(@RequestBody RifLogSaveRequest body) {
        log.info("saveRifLog: {}", body);

        RifLogSaveResponse rifLogSaveResponse = memberService.addRifLog(body);

        return ResponseEntity.ok(rifLogSaveResponse);
    }

    @Operation(summary = "권한 테스트", description = "권한 테스트하는 메서드입니다. 토큰을 넣으면 hello")
    @PostMapping(value = "/v/hello")
    public String hello() {

        log.info("info ={}", "hello");

        return "hello";
    }

    @Operation(summary = "비밀번호 변경", description = "비밀번호 변경하는 메서드입니다.")
    @PatchMapping(value = "/v/member/password")
    public ResponseEntity<MemberResponse> passwordChange(HttpServletRequest request,
                                                         @RequestParam String memberId,
                                                         @Validated @RequestBody PasswordChangeRequest passwordChangeRequest) {
        log.info("asd : {}", memberId);
        log.info("httpservletRequest info = {}", request);

        MemberResponse member = memberService.passwordChange(request, memberId, passwordChangeRequest);

        return ResponseEntity.ok(member);
    }

    @Operation(summary = "프로필 사진 변경", description = "프로필 사진을 변경하는 메서드입니다.")
    @PatchMapping(value = "/v/member/profile")
    public ResponseEntity<?> profileChange(@RequestBody MemberResponse changedProfile) {

        MemberResponse member = memberService.profileChange(changedProfile);

        log.info("changed profile info = {}", member);

        return ResponseEntity.ok(member);
    }

    @Operation(summary = "회원이름", description = "모든 회원의 이름을 반환하는 메서드입니다.")
    @GetMapping(value = "/member/name")
    public ResponseEntity<Map<String, Object>> getMemberNameAll() {
        List<GetMembersName> getNameAll = memberService.getMembersName();
        log.info("MemberNameAll info = {}", getNameAll);

        return ResponseEntity.ok(Map.of("members", getNameAll));
    }

    @Operation(summary = "회원 찾기", description = "이름으로 회원을 찾는 메서드입니다.")
    @GetMapping(value = "/v/member/search")
    public ResponseEntity<Map<String, Object>> findMembers(@RequestParam String name) {

        log.info("Search member name info = {}", name);

        List<FindMemberByName> repoResponse = memberService.findByName(name);

        return ResponseEntity.ok(Map.of("members", repoResponse));
    }

    @GetMapping("/ranking")
    @Operation(summary = "누적 경험치 Top 10 랭킹을 조회한다")
    public ResponseEntity<Map<String, Object>> findExpTop10Members() {

        List<MemberRankingResponse> expTop10Members = memberService.getFirst10ByOrderByExp();

        return ResponseEntity.ok(Map.of("members", expTop10Members));
    }

    // ranking?memberId=12345
    @GetMapping(value = "/v/ranking{memberId}")
    @Operation(summary = "누적 경험치 Top 10 그리고 나의 랭킹을 조회한다")
    public ResponseEntity<Map<String, Object>> findExpTop10MeMembers(@RequestParam(value = "memberId") String memberId) {

        List<MemberRankingResponse> expTop10MeMembers = memberService.getFirstAllByOrderByExp(memberId);
        log.info("ranking info = {}", expTop10MeMembers);
        return ResponseEntity.ok(Map.of("members", expTop10MeMembers));
    }
}
