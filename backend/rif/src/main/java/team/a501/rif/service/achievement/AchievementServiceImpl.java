package team.a501.rif.service.achievement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.a501.rif.domain.achievement.Achievement;
import team.a501.rif.dto.achievement.AchievementInfo;
import team.a501.rif.dto.achievement.AchievementSaveRequest;
import team.a501.rif.exception.ErrorCode;
import team.a501.rif.exception.RifCustomException;
import team.a501.rif.repository.achievement.AchievementRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;

    @Override
    public AchievementInfo save(AchievementSaveRequest achievementSaveRequest) {

        Achievement achievement =
                achievementRepository.save(Achievement.builder()
                        .tier(achievementSaveRequest.getTier())
                        .title(achievementSaveRequest.getTitle())
                        .description(achievementSaveRequest.getDescription())
                        .imgPath(achievementSaveRequest.getImgPath())
                        .tag(achievementSaveRequest.getTag())
                        .build());

        return AchievementInfo.from(achievement);
    }

    @Override
    public List<AchievementInfo> saveAll(List<AchievementSaveRequest> achievementSaveRequests) {

        List<AchievementInfo> achievementInfoList = new ArrayList<>();

        for (var e :
                achievementSaveRequests) {

            Achievement achievement =
                    achievementRepository.save(Achievement.builder()
                            .tier(e.getTier())
                            .title(e.getTitle())
                            .description(e.getDescription())
                            .imgPath(e.getImgPath())
                            .tag(e.getTag())
                            .build());

            achievementInfoList.add(AchievementInfo.from(achievement));
        }

        return achievementInfoList;
    }

    @Override
    public List<Achievement> findAll() {

        return achievementRepository.findAll();
    }

    @Override
    @Transactional
    public Achievement findById(Long id) {
        return achievementRepository.findById(id)
                .orElseThrow(() -> new RifCustomException(ErrorCode.NOT_ENOUGH_POINTS));
    }
}
