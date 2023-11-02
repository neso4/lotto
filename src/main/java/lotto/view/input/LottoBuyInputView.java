package lotto.view.input;

import lotto.validator.InputValidator;

public class LottoBuyInputView extends Input{

    private static final String PURCHASE_AMOUNT_PROMPT_MESSAGE = "구입할 Lotto 금액을 입력해 주세요.";

    private final InputValidator inputValidator;
    private final int denomination;

    public LottoBuyInputView(int denomination) {
        //LottoController에서 InputView가 InputValidator를 의존하는지 알 필요가 없다고 생각한다.
        this.inputValidator = new InputValidator();
        this.denomination = denomination;
    }

    public int requestLottoPurchaseAmount() {
        printRequestLottoPurchaseAmount();

        int amount = inputValidator.parseInt(readLine());
        inputValidator.checkIsValidLottoAmount(amount);
        inputValidator.checkIsMultipleOfDenomination(amount, denomination);

        return amount;
    }

    private static void printRequestLottoPurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT_PROMPT_MESSAGE);
    }

}
