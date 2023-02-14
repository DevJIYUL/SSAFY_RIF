package team.a501.rif.domain.achievement;

import team.a501.rif.dto.riflog.RifLogInfo;

import java.util.List;

public class AchievementCompleteChecker {

    private Integer rifUseCount;
    private Integer plasticSuccessCount;
    private Integer recycleSuccessCount;
    private Integer perfectSuccessCount;

    private AchievementCompleteChecker() {
        this.rifUseCount = 0;
        this.plasticSuccessCount = 0;
        this.recycleSuccessCount = 0;
        this.perfectSuccessCount = 0;
    }

    private void addRifUseCount() {
        this.rifUseCount++;
    }

    private void addPlasticSuccess() {
        this.plasticSuccessCount++;
    }

    private void addRecycleSuccess() {
        this.recycleSuccessCount++;
    }

    private void addPerfectSuccess() {
        this.perfectSuccessCount++;
    }

    public static AchievementCompleteChecker of(List<RifLogInfo> rifLogInfoList) {

        AchievementCompleteChecker instance = new AchievementCompleteChecker();

        for (var e :
                rifLogInfoList) {

            instance.addRifUseCount();

            Boolean isPlasticSuccess = false;
            Boolean isRecycleSuccess = false;

            if (e.getPlasticTotal() > 0 && e.getPlasticTotal().equals(e.getPlasticOk())) {

                isPlasticSuccess = true;
                instance.addPlasticSuccess();
            }

            if (e.getRecycleTotal() > 0 && e.getRecycleTotal().equals(e.getRecycleOk())) {

                isRecycleSuccess = true;
                instance.addRecycleSuccess();
            }

            if (isPlasticSuccess && isRecycleSuccess) {

                instance.addPerfectSuccess();
            }
        }

        return instance;
    }

    public Boolean isCompleted(AchievementTag achievementTag) {

        switch (achievementTag) {

            case RIF_FIRST_USE:
                if (rifUseCount > 0) return true;

            case PLASTIC_SUCCESS_FIRST:
                if (plasticSuccessCount > 0) return true;

            case RECYCLE_SUCCESS_FIRST:
                if (recycleSuccessCount > 0) return true;

            case RIF_PERFECT_FIRST:
                if (perfectSuccessCount > 0) return true;

            case PLASTIC_SUCCESS_TEN_TIMES:
                if (plasticSuccessCount >= 10) return true;

            case RECYCLE_SUCCESS_TEN_TIMES:
                if (recycleSuccessCount >= 10) return true;

            case PLASTIC_SUCCESS_FIVE_TIMES:
                if (plasticSuccessCount >= 5) return true;

            case RECYCLE_SUCCESS_FIVE_TIMES:
                if (recycleSuccessCount >= 5) return true;

            case RIF_PERFECT_FIVE_TIMES:
                if (perfectSuccessCount >= 5) return true;
        }

        return false;
    }
}
