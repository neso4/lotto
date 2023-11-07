package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class Utils {
    public static int convertStringToInt(String purchasePriceInput) {
        int purchasePrice = 0;
        for (int i = purchasePriceInput.length() - 1, numberOfDigit = 1; i >= 0; i--, numberOfDigit *= 10) {
            purchasePrice += ((purchasePriceInput.charAt(i) - '0') * numberOfDigit);
        }

        return purchasePrice;
    }

    public static List<Integer> makeLottoNumber() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}