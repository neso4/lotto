package lotto.model.lotto;

import java.util.Arrays;
import java.util.Comparator;

public enum LottoRank {
    FAIL(0, false, 0, ""),
    RANK5(3, false, 5_000, "3개 일치 (5,000원) - "),
    RANK4(4, false, 50_000, "4개 일치 (50,000원) - "),
    RANK3(5, false, 1_500_000, "5개 일치 (1,500,000원) - "),
    RANK2(5, true, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원) - "),
    RANK1(6, false, 2_000_000_000, "6개 일치 (2,000,000,000원) - ");


    private final int matchCount;
    private final boolean isBonusMatch;
    private final int price;

    private final String message;

    LottoRank(int matchCount, boolean isBonusMatch, int price, String message) {
        this.matchCount = matchCount;
        this.isBonusMatch = isBonusMatch;
        this.price = price;
        this.message = message;
    }

    public static LottoRank valueOf(int matchCount, boolean isBonusMatch) {
        if (matchCount == RANK2.matchCount && isBonusMatch){
            return RANK2;
        }
        return Arrays.stream(values())
                .filter(rank -> rank.isSameMatchCount(matchCount))
                .findAny()
                .orElse(FAIL);
    }

    public boolean isSameMatchCount(final int matchCount) {
        return this.matchCount == matchCount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusMatch() {
        return isBonusMatch;
    }

    public int getPrice() {
        return price;
    }

    public String getMessage() {
        return message;
    }


}
