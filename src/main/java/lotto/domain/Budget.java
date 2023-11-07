package lotto.domain;

import lotto.utils.ErrorMessage;

public class Budget {
    private final int budget;

    public Budget(int budget) {
        budgetValidation(budget);

        this.budget = budget;
    }

    public boolean canBuyALotto(int sumPayment){
        return this.budget>sumPayment;
    }

    private void budgetValidation(int budget) {
        if (budget < 0) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_ZERO_OR_MORE.getErrorMessage());
        }

        if (budget % 1000 != 0) {
            throw new IllegalArgumentException(ErrorMessage.UNITS_OF_1000.getErrorMessage());
        }
    }

    public double getProfit(long rewardSum){
        return rewardSum/(double)budget;
    }
}
