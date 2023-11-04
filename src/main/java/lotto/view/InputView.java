package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.util.Validator;

import java.util.List;

public class InputView {
    public static int inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return Validator.isValidPurchaseAmount(Console.readLine());
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
        return Validator.isValidWinningNumbers(Console.readLine());
    }

    public static int inputBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        return Validator.isValidBonusNumber(Console.readLine());
    }
}
