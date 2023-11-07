package lotto.views;

import camp.nextstep.edu.missionutils.Console;
import lotto.constants.Constants;

public class SystemMessage {

    public String getPurchasePrice() {
        System.out.println(Constants.PURCHASE_PRICE);
        return Console.readLine();
    }

    public String getLottoNumbers() {
        System.out.println("\n" + Constants.LOTTO_NUMBERS);
        return Console.readLine();
    }

    public String getBonusNumbers() {
        System.out.println("\n" + Constants.BONUS_NUMBER);
        return Console.readLine();
    }

    public void printPurchasedResult(Integer amount) {
        System.out.println("\n" + amount + Constants.PURCHASED_RESULT);
    }

    public void setPrintResult() {
        System.out.println(Constants.RESULT);
    }
}
