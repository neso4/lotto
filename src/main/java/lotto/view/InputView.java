package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.dto.WinningNumbersDto;
import lotto.util.Converter;
import lotto.util.validator.LottoNumberValidator;
import lotto.util.validator.Validator;
import lotto.util.validator.ValidatorFactory;

public class InputView {
    public static int inputPurchase() {
        String rawPurchase = input("구입금액을 입력해 주세요.");
        validateNumeric(rawPurchase);
        return Converter.convertToInt(rawPurchase);
    }

    public static WinningNumbersDto inputWinningNumbers() {
        String rawWinningNumbers = input("당첨 번호를 입력해 주세요.");
        return WinningNumbersDto.from(rawWinningNumbers);
    }

    public static int inputBonusNumber(List<Integer> winningNumbers) {
        String rawBonusNumber = input("보너스 번호를 입력해 주세요.");
        validateNumeric(rawBonusNumber);
        int bonusNumber = Integer.parseInt(rawBonusNumber);
        LottoNumberValidator.validateRange(bonusNumber);
        LottoNumberValidator.validateDuplicates(bonusNumber, winningNumbers);
        return bonusNumber;
    }

    private static void validateNumeric(String value) {
        ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
        Validator validator = validatorFactory.getValidator(InputView.class);
        validator.validate(value);
    }

    private static String input(String message) {
        System.out.println(message);
        return Console.readLine();
    }

    private InputView() {
    }
}
