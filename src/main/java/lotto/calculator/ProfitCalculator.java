package lotto.calculator;

import java.util.HashMap;

import static lotto.constants.ConstantValues.*;

public class ProfitCalculator {

    public static double caculateProfit(int prize, int cost) {
        return (double) prize / cost;
    }

    public static int caculatePrize(HashMap<String, Integer> score) {
        int prize = 0;
        for (String key : score.keySet()) {
            prize += getPrize(key) * score.get(key);
        }
        return prize;
    }

    private static int getPrize(String key) {
        if (key.equals("1등")) {
            return ONE_PRIZE.getValue();
        }
        if (key.equals("2등")) {
            return TWO_PRIZE.getValue();
        }
        if (key.equals("3등")) {
            return THREE_PRIZE.getValue();
        }
        if (key.equals("4등")) {
            return FOUR_PRIZE.getValue();
        }
        return FIVE_PRIZE.getValue();
    }

}
