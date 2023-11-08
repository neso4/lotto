package lotto.util;

import static lotto.util.rule.GameRule.LOTTO_SIZE;
import static lotto.util.rule.GameRule.MAX_LOTTO_RANGE;
import static lotto.util.rule.GameRule.MIN_LOTTO_RANGE;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Collectors;

public class AutomaticGenerator {

    public static List<Integer> generateLottoNumbers() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                MIN_LOTTO_RANGE.getValue(),
                MAX_LOTTO_RANGE.getValue(),
                LOTTO_SIZE.getValue());
        return numbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

}
