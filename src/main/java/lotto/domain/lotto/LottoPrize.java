package lotto.domain.lotto;

import java.util.List;
import lotto.domain.money.Money;
import lotto.domain.money.Price;

/**
 * WinningLotto와 Lotto를 비교하여 일치하는 번호 및 보너스 번호를 계산하여 로또 등 수를 나타낼 때 사용되는 클래스입니다.
 */
public enum LottoPrize {
    FIRST(1, Price.from(2_000_000_000), 6, false),
    SECOND(2, Price.from(30_000_000), 5, true),
    THIRD(3, Price.from(1_500_000), 5, false),
    FOURTH(4, Price.from(50_000), 4, false),
    FIFTH(5, Price.from(5_000), 3, false),
    NONE(0, Price.zero(), 0, false);

    private static final List<LottoPrize> lottoPrizes = List.of(values());
    private final int place;
    private final Price price;
    private final int matchedCount;
    private final boolean hasBonusNumber;

    LottoPrize(
            final int place,
            final Price price,
            final int matchedCount,
            final boolean hasBonusNumber
    ) {
        this.place = place;
        this.price = price;
        this.matchedCount = matchedCount;
        this.hasBonusNumber = hasBonusNumber;
    }

    /**
     * 일치하는 로또 번호 개수, 보너스 번호 유무에 따라 적절한 등수를 반환합니다. 만약 1~5등에 속하지 않는 경우라면 전부 NONE을 반환합니다.
     */
    public static LottoPrize of(
            final int matchedCount,
            final boolean hasBonusNumber
    ) {
        if (hasBonusNumber && matchedCount == SECOND.matchedCount) {
            return SECOND;
        }
        return lottoPrizes.stream()
                .filter(lottoPrize -> !lottoPrize.hasBonusNumber)
                .filter(lottoPrize -> lottoPrize.matchedCount == matchedCount)
                .findFirst()
                .orElse(LottoPrize.NONE);
    }

    public Money intoMoney() {
        return price.intoMoney();
    }
}
