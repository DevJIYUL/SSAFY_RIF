package team.a501.rif.service.badge;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.a501.rif.domain.badge.Badge;
import team.a501.rif.dto.badge.BadgeSaveRequest;
import team.a501.rif.repository.badge.BadgeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class BadgeServiceImpl implements BadgeService {

    private final BadgeRepository badgeRepository;

    @Override
    @Transactional
    public Badge save(BadgeSaveRequest dto) {

        Badge badge = badgeRepository.save(Badge.builder()
                .tier(dto.getTier())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .badgeImgPath(dto.getBadgeImgPath())
                .build());

        return badge;
    }

    @Override
    public Badge findById(Long id) {
        Badge badge = badgeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException());
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
