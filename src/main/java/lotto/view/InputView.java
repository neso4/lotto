package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.util.validator.AmountValidator;

public class InputView {
    private final String INPUT_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private final String INPUT_WINNER_LOTTO_MESSAGE = "당첨 번호를 입력해 주세요.";
    private final String INPUT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    public String readAmount(){
        System.out.println(INPUT_AMOUNT_MESSAGE);
        String input = Console.readLine();
        new AmountValidator().validation(input);
        return input;
    }
    public String readWinnerLottoNumbers(){
        System.out.println(INPUT_WINNER_LOTTO_MESSAGE);
        return Console.readLine();
    }
    public String readBonusNumber(){
        System.out.println();
        System.out.println(INPUT_BONUS_NUMBER);
        return Console.readLine();
    }
}
