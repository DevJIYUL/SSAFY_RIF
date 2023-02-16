package team.a501.rif.domain.riflog;

import lombok.NoArgsConstructor;
import team.a501.rif.exception.ErrorCode;
import team.a501.rif.exception.RifCustomException;

@NoArgsConstructor
public class RifScore {
    private static final Integer SCORE_MULTIPLIER = 50;

    public static Integer getScoreOf(Integer plasticTotal, Integer plasticOk, Integer recycleTotal, Integer recycleOk) {

        Integer numerator = plasticOk + recycleOk;
        Integer denominator = plasticTotal + recycleTotal;

        if (denominator <= 0)
            return -1;

        Double successRatio = numerator.doubleValue() / denominator;

        Double okCountFactor = Math.log(Math.max(1, (plasticOk + recycleOk) * 2));

        Long result = Math.round(SCORE_MULTIPLIER * successRatio * okCountFactor);

        return result.intValue();
    }
}
