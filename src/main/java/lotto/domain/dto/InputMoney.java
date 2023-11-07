package lotto.domain.dto;


import static lotto.validatior.InputPurchaseAmountValidator.validatePurchaseAmount;

public class InputMoney {
    private final String money;

    public InputMoney(String money) {
        validatePurchaseAmount(money);
        this.money = money;
    }

    public int getMoney() {
        return Integer.parseInt(money);
    }
}
