package lotto.View;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.Domain.BonusNumber;

public class InputView {
    private final static String INPUT_LOTTO_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private final static String INPUT_LOTTO_NUMBER_MESSAGE = "당첨 번호를 입력해 주세요.";
    private final static String INPUT_BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";
    private List<Integer> winningNumberList;
    public static String inputLottoPurchaseAmount() {

        System.out.println(INPUT_LOTTO_PURCHASE_AMOUNT_MESSAGE);
        String inputPurchaseMoney = Console.readLine();

        return inputPurchaseMoney;
    }

    public static List<Integer> inputLottoNumbers() {

        System.out.println(INPUT_LOTTO_NUMBER_MESSAGE);

        return Arrays.stream(Console.readLine().split(","))
                .map(Integer::parseInt)
                .toList();
    }

    public static String inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        String bonusNumberInput = Console.readLine();

        return bonusNumberInput;
    }
}
