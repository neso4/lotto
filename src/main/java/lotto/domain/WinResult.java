package lotto.domain;

import java.util.HashMap;
import java.util.Map;

import static lotto.util.LottoNumber.LOTTO_SAME_NUMBER_FIVE;
import static lotto.util.LottoNumber.LOTTO_SAME_NUMBER_TWO;
import static lotto.util.WinnerRank.*;

public class WinResult {
    private static final int winResultInit = 0;
    private static final int incrementWinResult = 1;

    private Map<Integer, Integer> winResult;

    public WinResult() {
        winResult = new HashMap<>();
        initWinResult();
    }

    private void initWinResult() {
        winResult.put(THREE.getRankKey(), winResultInit);
        winResult.put(FOUR.getRankKey(), winResultInit);
        winResult.put(FIVE_WITHOUT_BOUNUS.getRankKey(), winResultInit);
        winResult.put(FIVE_WITH_BONUS.getRankKey(), winResultInit);
        winResult.put(SIX.getRankKey(), winResultInit);
    }

    public Integer getWinResultValue(int rankKey) {
        return winResult.get(rankKey);
    }

    public void increaseWinResultValue(int rankKey) {
        winResult.put(rankKey, winResult.get(rankKey) + incrementWinResult);
    }

    public boolean isOverTwo(int sameNumberCount) {
        return sameNumberCount > LOTTO_SAME_NUMBER_TWO.getValue();
    }

    public boolean isFiveSame(int sameNumberCount) {
        return sameNumberCount == LOTTO_SAME_NUMBER_FIVE.getValue();
    }
}
