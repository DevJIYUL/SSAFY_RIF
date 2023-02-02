package team.a501.rif.service.achievement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.a501.rif.domain.achievement.Achievement;
import team.a501.rif.dto.achievement.AchievementSaveRequest;
import team.a501.rif.exception.NoSuchEntityException;
import team.a501.rif.repository.achievement.AchievementRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class AchievementServiceImpl implements AchievementService{

    private final AchievementRepository achievementRepository;

    @Override
    @Transactional
    public Achievement save(AchievementSaveRequest dto) {
        return achievementRepository.save(Achievement.builder()
                .tier(dto.getTier())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .achievementImgPath(dto.getImgPath())
                .build());
    }

    @Override
    @Transactional
    public Achievement findById(Long id) {
        return achievementRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException(Achievement.class.getSimpleName()));
    }
}