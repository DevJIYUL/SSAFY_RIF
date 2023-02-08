package team.a501.rif.controller.achievement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.a501.rif.dto.achievement.AchievementInfo;
import team.a501.rif.dto.achievement.AchievementSaveRequest;
import team.a501.rif.service.achievement.AchievementService;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "AchievementController")
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
@RestController
public class AchievementController {

    private final AchievementService achievementService;

    @PostMapping("/achievement")
    @Operation(summary = "업적 추가")
    public ResponseEntity<AchievementInfo> saveAchievement(@RequestBody AchievementSaveRequest request) {

        AchievementInfo achievementInfo =
                AchievementInfo.from(achievementService.save(request));

        return ResponseEntity.ok(achievementInfo);
    }

    @GetMapping("/achievement")
    @Operation(summary = "모든 종류의 업적을 반환")
    public ResponseEntity<List<AchievementInfo>> findAllAchievement() {

        List<AchievementInfo> achievementInfoList = achievementService.findAll()
                .stream()
                .map(achievement -> AchievementInfo.from(achievement))
                .collect(Collectors.toList());

        return ResponseEntity.ok(achievementInfoList);
    }
}
