package view;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static generalexception.GeneralExceptionHandler.validateNumberExceptionHandler;

public class Display {

    private static final String STRING_PURCHASE_AMOUNT_INPUT = "구매금액을 입력해주세요";
    public static final String PURCHASE_AMOUNT_UNDER_ERROR_MESSAGE = "=[ERROR] 구매 금액이 1000원 미만입니다.";
    public static final String PURCHASE_AMOUNT_NOT_DIVIDE_ERROR_MESSAGE = "[ERROR] 구매금액이 천원 단위가 아닙니다.";
    private int lottoPurchaseAmount;

    public Display() {
        setLottoPurchaseAmount();
    }

    public Display(int lottoPurchaseAmount) { //test 코드를 위한 생성자
        validateMinimumPurchaseAmount(lottoPurchaseAmount);
        validateNotDividePurchaseAmount(lottoPurchaseAmount);
        this.lottoPurchaseAmount = lottoPurchaseAmount;
    }

    private void setLottoPurchaseAmount() {
        System.out.println(STRING_PURCHASE_AMOUNT_INPUT);
        int userPurchaseAmount = validateNumberExceptionHandler(readLine());
        validateMinimumPurchaseAmount(userPurchaseAmount);
        this.lottoPurchaseAmount = userPurchaseAmount;
    }

    private void validateMinimumPurchaseAmount(int userPurchaseAmount) {
        if (userPurchaseAmount < 1000) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_UNDER_ERROR_MESSAGE);
        }
    }

    private void validateNotDividePurchaseAmount(int userPurchaseAmount) {
        if (userPurchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_NOT_DIVIDE_ERROR_MESSAGE);
        }

    }
}
