package lotto.domain;

import lotto.validator.system.domain.BudgetValidator;

public class Budget {
    private final int budget;

    public Budget(String budget){
        BudgetValidator.validateBudget(toNumber(budget));
        this.budget = toNumber(budget);
    }


    private int toNumber(String budget){
        return Integer.parseInt(budget);
    }

    public int getBudget(){
        BudgetValidator.validateBudget(budget);
        return budget;
    }


}
