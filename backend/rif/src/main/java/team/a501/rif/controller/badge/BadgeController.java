package team.a501.rif.controller.badge;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.a501.rif.dto.badge.BadgeInfo;
import team.a501.rif.dto.badge.BadgeSaveRequest;
import team.a501.rif.service.badge.BadgeService;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "BadgeController")
@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class BadgeController {

    private final BadgeService badgeService;

    @PostMapping("/badge")
    @Operation(summary = "뱃지 추가")
    public ResponseEntity<BadgeInfo> saveBadge(@RequestBody BadgeSaveRequest request){

        BadgeInfo badgeInfo = BadgeInfo.from(badgeService.save(request));

        return ResponseEntity.ok(badgeInfo);
    }

    @GetMapping("/badge")
    @Operation(summary = "모든 종류의 뱃지 반환")
    public ResponseEntity<List<BadgeInfo>> findAllBadges(){


        List<BadgeInfo> badgeInfoList = badgeService.findAll()
                .stream()
                .map(badge -> BadgeInfo.from(badge))
                .collect(Collectors.toList());

        return ResponseEntity.ok(badgeInfoList);
    }
}
