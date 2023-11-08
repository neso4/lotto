package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.util.MessageConst;
import lotto.util.Validator;

public class InputView {

    public static final String INPUT_MONEY_MSG = "구입금액을 입력해 주세요.";
    public static final String INPUT_LOTTO_MSG = "당첨 번호를 입력해 주세요.";
    public static final String INPUT_BONUS_MSG = "보너스 번호를 입력해 주세요.";

    OutputView outputView = new OutputView();

    public int inputMoney() {
        try {
            System.out.println(INPUT_MONEY_MSG);
            String money = Console.readLine();
            Validator.getValidatedMoney(money);
            return Integer.parseInt(money);
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception);
            return inputMoney();
        }
    }

    public List<Integer> inputLottoNumbers() {
        System.out.println();
        System.out.println(INPUT_LOTTO_MSG);
        String lottoNumbers = Console.readLine();
        return Arrays.stream(lottoNumbers.split(MessageConst.DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public int inputBonusNumber() {
        System.out.println();
        System.out.println(INPUT_BONUS_MSG);
        return Integer.parseInt(Console.readLine());
    }
}
