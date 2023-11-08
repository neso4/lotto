package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private static final String MONEY_ERROR = "[ERROR] 1000원 단위의 값을 입력해야 합니다.";
    private static final int LOTTO_PRICE = 1000;
    private int money;
    private List<Lotto> lottos;

    public Customer(int money) {
        validateMoney(money);
        this.money = money;
    }

    public void pay() {
        IssuingMachine issuingMachine = IssuingMachine.turnOn(money);
        lottos = issuingMachine.issueLotto();
    }

    private void validateMoney(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(MONEY_ERROR);
        }
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
