package team.a501.rif.controller.badge;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.a501.rif.dto.badge.BadgeSave;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.domain.badge.Badges;
import team.a501.rif.repository.badge.BadgeRepository;

@RequiredArgsConstructor
@RestController
public class BadgeController {

    private final BadgeRepository badgeRepository;

    @PostMapping("/badge")
    public ResponseEntity<Badge> createBadge(@RequestBody BadgeSave badgeSaveRequest){

        Badge badge = badgeRepository.save(badgeSaveRequest.toEntity());

        return new ResponseEntity<>(badge, HttpStatus.OK);
    }

    @GetMapping("/badge")
    public ResponseEntity<Badges> allBadges(){

        Badges badges = new Badges(badgeRepository.findAll());

        return new ResponseEntity<>(badges, HttpStatus.OK);
    }
}
