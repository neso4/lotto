package lotto.domain;

import lotto.constants.Rank;

import java.util.Collections;
import java.util.List;

import static lotto.model.GameStatistics.LOTTERY_PRICE;

public class Calculator {

    public static Integer calculateTotalPrize(List<Rank> ranks) {
        Integer totalPrize = 0;
        for (Rank rank : Rank.values()) {
            int rankFrequency = Collections.frequency(ranks, rank);
            totalPrize += rank.calculatePrize(rankFrequency);
        }
        return totalPrize;
    }

    public static Double calculatePricePrizeRatio(Integer totalPrize, Integer price) {
        return (double) totalPrize / price * 100;
    }

    public static Integer calculateCount(Integer budget) {
        return budget / LOTTERY_PRICE;
    }
}
