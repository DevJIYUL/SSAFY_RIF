package team.a501.rif.controller.badge;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.a501.rif.dto.badge.BadgeInfo;
import team.a501.rif.dto.badge.BadgeSaveRequest;
import team.a501.rif.service.badge.BadgeService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class BadgeController {

    private final BadgeService badgeService;

    @PostMapping("/badge")
    public ResponseEntity<BadgeInfo> createBadge(@RequestBody BadgeSaveRequest request){

        BadgeInfo badgeInfo = BadgeInfo.from(badgeService.save(request));

        return ResponseEntity.ok(badgeInfo);
    }

    @GetMapping("/badge")
    public ResponseEntity<List<BadgeInfo>> getAllBadges(){


        List<BadgeInfo> badgeInfoList = badgeService.findAll()
                .stream()
                .map(badge -> BadgeInfo.from(badge))
                .collect(Collectors.toList());

        return ResponseEntity.ok(badgeInfoList);
    }
}
