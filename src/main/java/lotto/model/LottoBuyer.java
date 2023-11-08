package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoBuyer {

    private static final String CASH_IS_NOT_DIVIDED_BY_LOTTO_PRICE = "1000원 단위의 금액으로 구매가 가능합니다.";
    private static final int LOTTO_PRICE = 1000;

    private int cash;
    private int numbersToBuy;
    private List<Lotto> lottos;

    public LottoBuyer(int cash) {
        checkBuyAvailabilty(cash);
        this.cash = cash;
        this.numbersToBuy = calculateNumbersToBuy(cash);
        this.lottos = new ArrayList<>();
    }

    private void checkBuyAvailabilty(int cash) {
        if (cash % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(CASH_IS_NOT_DIVIDED_BY_LOTTO_PRICE);
        }
    }

    private int calculateNumbersToBuy(int cash) {
        return cash / LOTTO_PRICE;
    }

    public void purchase() {
        for (int i = 0; i < numbersToBuy; i++) {
            lottos.add(Lotto.create());
        }
    }

}
