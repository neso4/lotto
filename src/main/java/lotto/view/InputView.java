package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.text.MessageFormat;
import java.util.List;
import lotto.enums.ConstVariable;
import lotto.util.ValidationUtil;

public class InputView {

    public static final String PURCHASE_AMOUNT_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String LOTTO_COUNT_MESSAGE = "{0}개를 구매했습니다.";
    public static final String WIN_LOTTO_NUMBERS_INPUT_MESSAGE = "당첨 번호를 입력해 주세요.";
    public static final String BONUS_NUMBER_INPUT_MESSAGE = "보너스 번호를 입력해 주세요.";

    public static int getGeneratedLottoCount() {
        System.out.println(PURCHASE_AMOUNT_INPUT_MESSAGE);
        String amount = Console.readLine();
        System.out.println();
        ValidationUtil.isValidAmount(amount);
        int count = Integer.parseInt(amount) / ConstVariable.STANDARD.getValue();
        System.out.println(MessageFormat.format(LOTTO_COUNT_MESSAGE, count));
        return count;
    }

    public static List<Integer> getWinLottoNumbers() {
        System.out.println(WIN_LOTTO_NUMBERS_INPUT_MESSAGE);
        String winNumbers = Console.readLine();
        System.out.println();
        return ValidationUtil.isValidWinNumbers(winNumbers);
    }

    public static int getWinBonusNumber() {
        System.out.println(BONUS_NUMBER_INPUT_MESSAGE);
        int bonusNumber = Integer.parseInt(Console.readLine());
        System.out.println();
        return bonusNumber;
    }
}
