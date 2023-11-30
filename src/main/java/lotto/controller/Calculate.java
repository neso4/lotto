package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.Lotto;
import lotto.model.Constants;
import lotto.model.PRIZE_TYPE;

public class Calculate {

    public static double earningRate(Map<PRIZE_TYPE, Integer> result, int inputMoney) {
        double earn = 0.0;
        for (PRIZE_TYPE prizeType : result.keySet()) {
            earn += (double) Constants.PRIZE_MONEY.get(prizeType) *
                    result.get(prizeType);
        }

        return earn / inputMoney * 100.0;
    }

    public static Map<PRIZE_TYPE, Integer> countOfPrize(Lotto prizeNumbers, int bonusNumber,
                                                        List<Lotto> lottos) {
        var result = new HashMap<PRIZE_TYPE, Integer>();

        for (PRIZE_TYPE prizeType : PRIZE_TYPE.values()) {
            result.put(prizeType, 0);
        }

        for (Lotto lotto : lottos) {
            var lottoResult = lotto.calculatePrize(prizeNumbers, bonusNumber);
            int value = result.get(lottoResult);
            result.put(lottoResult, value + 1);
        }

        return result;
    }

    public static List<Lotto> makeLottos(int numberOfLotto) {
        List<Lotto> lotto = new ArrayList<>();
        for (int i = 0; i < numberOfLotto; ++i) {
            lotto.add(makeLotto());
        }

        return lotto;
    }

    public static Lotto makeLotto() {
        List<Integer> num = Randoms
                .pickUniqueNumbersInRange(
                        Constants.LOTTO_RANGE_START,
                        Constants.LOTTO_RANGE_END,
                        Constants.LOTTO_NUMBER_COUNT
                );
        Collections.sort(num);
        return new Lotto(num);
    }

}
