package lotto;

import lotto.purchasing.PurchaseAmount;

public class Application {
    public static void main(String[] args) {
        ResultBoard resultBoard = new ResultBoard(new PurchaseAmount());

        resultBoard.show();
    }
}
