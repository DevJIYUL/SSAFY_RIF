package team.a501.rif.service.badge;

import team.a501.rif.domain.badge.Badge;
import team.a501.rif.dto.badge.BadgeInfo;
import team.a501.rif.dto.badge.BadgeSaveRequest;

import java.util.List;

public interface BadgeService {

    BadgeInfo save(BadgeSaveRequest badgeSaveRequest);

    List<BadgeInfo> saveAll(List<BadgeSaveRequest> badgeSaveRequestList);

    Badge findById(Long id);

    Badge getRandomBadge();

    List<Badge> findAll();

    void deleteById(Long id);

    void deleteAll();
}
