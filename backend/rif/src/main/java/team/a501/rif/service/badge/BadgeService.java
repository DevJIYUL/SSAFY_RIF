package team.a501.rif.service.badge;

import team.a501.rif.domain.badge.Badge;
import team.a501.rif.dto.badge.BadgeInfo;
import team.a501.rif.dto.badge.BadgeSaveRequest;

public interface BadgeService {

    Badge save(BadgeSaveRequest badge);

    Badge findById(Long id);

    Badge getRandomBadge();

    void deleteById(Long id);

    void deleteAll();
}
