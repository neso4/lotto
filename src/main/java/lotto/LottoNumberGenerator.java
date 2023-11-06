package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

import static lotto.GameConfig.*;

public class LottoNumberGenerator {
    public static List<Integer> generateRandomLottoNumber() {
        return Randoms.pickUniqueNumbersInRange(
                LOTTO_NUMBER_LOWER_BOUND,
                LOTTO_NUMBER_UPPER_BOUND,
                NUMBER_OF_LOTTO_NUMBERS
        );
    }
}
