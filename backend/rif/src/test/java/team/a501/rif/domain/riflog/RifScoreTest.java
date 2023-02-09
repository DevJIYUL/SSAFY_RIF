package team.a501.rif.domain.riflog;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RifScoreTest {

    @DisplayName("Rif Score 계산")
    @Test
    void rifScoreCalculationTest(){

        Integer score = RifScore.getScoreOf(0, 0, 1, 1);

        System.out.println("score = " + score);
    }

}