package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.model.Lotto;
import lotto.util.converter.IntegerConverter;
import lotto.util.converter.IntegerListConverter;
import lotto.util.validator.BlankValidator;
import lotto.util.validator.DigitsOnlyValidator;

public class InputView {
    public int readUserMoney() {
        String input = Console.readLine();
        validate(input);
        return IntegerConverter.convert(input);
    }

    public Lotto readWinningNumbers() {
        String input = Console.readLine();
        validate(input);
        return new Lotto(IntegerListConverter.convert(input));
    }

    public Integer readBonusNumber() {
        String input = Console.readLine();
        validate(input);
        return IntegerConverter.convert(input);
    }

    private void validate(String value) {
        BlankValidator.validate(value);
        DigitsOnlyValidator.validate(value);
    }
}
