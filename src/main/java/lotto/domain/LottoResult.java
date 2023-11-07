package lotto.domain;

import static lotto.exception.ErrorMessage.WRONG_RANK_STATE;

import java.util.Arrays;
import java.util.List;

public enum LottoResult {
    FIRST_PLACE(1, 6, 2_000_000_000, Arrays.asList(true, false)),
    SECOND_PLACE(2, 5, 30_000_000, Arrays.asList(true)),
    THIRD_PLACE(3, 5, 1_500_000, Arrays.asList(true, false)),
    FOURTH_PLACE(4, 4, 50_000, Arrays.asList(true, false)),
    FIFTH_PLACE(5, 3, 5_000, Arrays.asList(true, false));

    private final int rank;
    private final int lottoMatchCount;
    private final int lottoWinningAmount;
    private final List<Boolean> isMatchBonusNumber;

    LottoResult(int rank, int lottoMatchCount, int lottoWinningAmount, List<Boolean> isMatchBonusNumber) {
        this.rank = rank;
        this.lottoMatchCount = lottoMatchCount;
        this.lottoWinningAmount = lottoWinningAmount;
        this.isMatchBonusNumber = isMatchBonusNumber;
    }

    public static int getWinningAmount(int lottoMatchCount, Boolean isMatchBonusNumber) {
        for (LottoResult result : values()) {
            if (result.lottoMatchCount == lottoMatchCount && result.isMatchBonusNumber.contains(isMatchBonusNumber)) {
                return result.lottoWinningAmount;
            }
        }
        return 0;
    }

    public static int getRankCount(int lottoMatchCount, Boolean isMatchBonusNumber) {
        for (LottoResult result : values()) {
            if (result.lottoMatchCount == lottoMatchCount && result.isMatchBonusNumber.contains(isMatchBonusNumber)) {
                return result.rank;
            }
        }
        throw new IllegalStateException(WRONG_RANK_STATE);
    }

    public int getRank() {
        return rank;
    }

    public int getLottoMatchCount() {
        return lottoMatchCount;
    }

    public int getLottoWinningAmount() {
        return lottoWinningAmount;
    }

    public List<Boolean> getIsMatchBonusNumber() {
        return isMatchBonusNumber;
    }

}
