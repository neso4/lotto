package lotto.model;


import static lotto.util.Parser.parseString;
import static lotto.util.validators.WinningNumbersValidator.validateWinningNumbers;

import java.util.List;
import lotto.util.exception.input.ConstructionErrorException;


public class WinningNumbers {
    private final List<Integer> winning;

    private WinningNumbers() {
        throw new ConstructionErrorException();
    }

    public WinningNumbers(String winning) {
        validateWinningNumbers(winning);
        this.winning = parseString(winning).stream()
                .map(Integer::valueOf)
                .sorted()
                .toList();
    }

    public boolean contains(Integer number) {
        return winning.contains(number);
    }
}
