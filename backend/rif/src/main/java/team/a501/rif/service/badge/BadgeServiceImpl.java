package team.a501.rif.service.badge;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.dto.badge.BadgeInfo;
import team.a501.rif.dto.badge.BadgeSaveRequest;
import team.a501.rif.exception.ErrorCode;
import team.a501.rif.exception.RifCustomException;
import team.a501.rif.repository.badge.BadgeRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Transactional
@Service
public class BadgeServiceImpl implements BadgeService {

    private final BadgeRepository badgeRepository;

    @Override
    public BadgeInfo save(BadgeSaveRequest badgeSaveRequest) {

        Badge badge = badgeRepository.save(Badge.builder()
                .tier(badgeSaveRequest.getTier())
                .title(badgeSaveRequest.getTitle())
                .description(badgeSaveRequest.getDescription())
                .imgPath(badgeSaveRequest.getImgPath())
                .build());

        return BadgeInfo.from(badge);
    }

    @Override
    public List<BadgeInfo> saveAll(List<BadgeSaveRequest> badgeSaveRequestList) {

        List<BadgeInfo> badgeInfoList = new ArrayList<>();

        for (var e :
                badgeSaveRequestList) {

            Badge badge = badgeRepository.save(Badge.builder()
                    .tier(e.getTier())
                    .title(e.getTitle())
                    .description(e.getDescription())
                    .imgPath(e.getImgPath())
                    .build());

            badgeInfoList.add(BadgeInfo.from(badge));
        }

        return badgeInfoList;
    }

    @Override
    public Badge findById(Long id) {

        Badge badge = badgeRepository.findById(id)
                .orElseThrow(() -> new RifCustomException(ErrorCode.ENTITY_INSTANCE_NOT_FOUND));

        return badge;
    }

    @Override
    public Badge getRandomBadge() {

        List<Badge> allBadges = badgeRepository.findAll();

        Random random = new Random();

        Integer bound = allBadges.size();

        Badge badge = allBadges.get(random.nextInt(bound));

        return badge;
    }

    @Override
    public List<Badge> findAll() {

        return badgeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        badgeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {

        badgeRepository.deleteAll();
    }
}
