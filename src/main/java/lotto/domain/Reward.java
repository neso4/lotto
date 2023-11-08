package lotto.domain;

import lotto.utils.CalculationUtil;

public class Reward {
    private final Long reward;

    public Reward(Long reward) {
        this.reward = reward;
    }

    public double calcProfitRate(Long totalMoney) {
        return CalculationUtil.toPercentage(totalMoney, reward);
    }
}
