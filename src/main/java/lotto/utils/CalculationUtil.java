package lotto.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculationUtil {
    public static double toPercentage(Long boonMo, Long boonja) {
        BigDecimal moneyTimesHundred = BigDecimal.valueOf(boonja).multiply(BigDecimal.valueOf(100));
        return moneyTimesHundred.divide(BigDecimal.valueOf(boonMo), 1, RoundingMode.HALF_UP).doubleValue();
    }
}
