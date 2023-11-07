package view;

import validator.LottoPurchaseValidator;
import camp.nextstep.edu.missionutils.Console;

public class LottoPurchaseInputView {
    LottoPurchaseValidator lottoPurchaseValidator = new LottoPurchaseValidator();

    public int readLottoPurchaseValidator() {
        System.out.println("구입금액을 입력해 주세요.");
        String lottoPurchaseAmount = Console.readLine();
        int lottoPurchasePrice = lottoPurchaseValidator.validPurchaseAmount(lottoPurchaseAmount);
        return lottoPurchasePrice;
    }
}