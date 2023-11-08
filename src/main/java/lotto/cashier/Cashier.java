package lotto.cashier;

import lotto.lottocompany.Reward;
import lotto.lottocompany.RewardHistory;

public class Cashier {

    private ReceivedAmount receivedAmount;
    private int totalRewardAmount;

    public int calculateLottoCount(int purchaseAmount) throws IllegalArgumentException{
        receivedAmount = ReceivedAmount.create(purchaseAmount);
        return purchaseAmount / 1000;
    }

    public int calculateTotalRewardAmount(RewardHistory rewardHistory) {
        totalRewardAmount = rewardHistory.getHistory().stream()
                .mapToInt(Reward::getPrizeMoney)
                .sum();
        return totalRewardAmount;
    }

    public double calculateProfitRate() {
        double rateOfReturn = (double) totalRewardAmount / (double) receivedAmount.getAmount() * 100;
        return  Math.round(rateOfReturn * 10) / 10.0;
    }

}
