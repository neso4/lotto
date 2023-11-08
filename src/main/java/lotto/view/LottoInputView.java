package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.validate.LottoValidation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoInputView {
    private static final String COMMA = ",";
    private static final String PURCHASE_AMOUNT_ERROR_MSG = "[ERROR] 구입 금액은 1,000원 단위여야 합니다.";
    private static final String WINNING_NUMBER_ERROR_MSG = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    private static final String BONUS_NUMBER_ERROR_MSG = "[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.";
    private static final String DUPLICATE_BONUS_NUMBER_ERROR_MSG = "[ERROR] 보너스 번호는 로또 번호에 포함되어 있지 않은 숫자여야 합니다.";

    public int inputPurchaseAmount(final LottoValidation lottoValidation) {
        String userInput = Console.readLine();
        if (!lottoValidation.validate(userInput)) throw new IllegalArgumentException(PURCHASE_AMOUNT_ERROR_MSG);
        return Integer.parseInt(userInput);
    }

    public List<Integer> inputWinningNumber(final LottoValidation lottoValidation) {
        String userInput = Console.readLine();
        if (!lottoValidation.validate(userInput)) throw new IllegalArgumentException(WINNING_NUMBER_ERROR_MSG);
        return Arrays.stream(userInput.split(COMMA))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public int inputBonusNumber(final LottoValidation lottoValidation, final List<Integer> winningNumbers) {
        String userInput = Console.readLine();
        if (!lottoValidation.validate(userInput)) throw new IllegalArgumentException(BONUS_NUMBER_ERROR_MSG);

        int bonusNumber = Integer.parseInt(userInput);
        if (winningNumbers.contains(bonusNumber)) throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER_ERROR_MSG);
        return bonusNumber;
    }
}
