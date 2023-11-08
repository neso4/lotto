package lotto.constant;

import java.util.List;

public class LottoConstant {
    public static final int MAX_PURCHASE_AMOUNT = 100_000;

    public static final int LOTTO_SIZE = 6;

    public static final List<LotteryRank> LOTTERY_RANKS = List.of(
            LotteryRank.FIFTH_PRIZE,
            LotteryRank.FOURTH_PRIZE,
            LotteryRank.THIRD_PRIZE,
            LotteryRank.SECOND_PRIZE,
            LotteryRank.FIRST_PRIZE
    );
}
