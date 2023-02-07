package team.a501.rif.controller.achievement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.a501.rif.dto.achievement.AchievementInfo;
import team.a501.rif.dto.achievement.AchievementSaveRequest;
import team.a501.rif.service.achievement.AchievementService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
@RestController
public class AchievementController {

    private final AchievementService achievementService;

    @PostMapping("/achievement")
    public ResponseEntity<AchievementInfo> createAchievement(@RequestBody AchievementSaveRequest request) {

        AchievementInfo achievementInfo =
                AchievementInfo.from(achievementService.save(request));

        return ResponseEntity.ok(achievementInfo);
    }

    @GetMapping("/achievement")
    public ResponseEntity<List<AchievementInfo>> getAllAchievement() {

        List<AchievementInfo> achievementInfoList = achievementService.findAll()
                .stream()
                .map(achievement -> AchievementInfo.from(achievement))
                .collect(Collectors.toList());

        return ResponseEntity.ok(achievementInfoList);
    }
}
