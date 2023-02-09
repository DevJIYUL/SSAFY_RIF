package team.a501.rif.domain.riflog;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RifScore {
    private static final Integer SCORE_MULTIPLIER = 50;

    public static Integer getScoreOf(Integer plasticTotal, Integer plasticOk, Integer recycleTotal, Integer recycleOk) {

        Integer numerator = plasticOk + recycleOk;
        Integer denominator = plasticTotal + recycleTotal;

        Double successRatio = numerator.doubleValue() / denominator;

        Double okCountFactor = Math.log(Math.max(1, (plasticOk + recycleOk) * 2));

        Long result = Math.round(SCORE_MULTIPLIER * successRatio * okCountFactor);

        return result.intValue();
    }
}
