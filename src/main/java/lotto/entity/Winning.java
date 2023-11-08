package lotto.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static lotto.property.LottoProperty.DELIMITER;
import static lotto.property.ValidationProperty.WINNING;
import static lotto.property.ValidationProperty.WINNINGS;
import static lotto.validation.ValidationForm.verifyFormatForInputValue;

public class Winning {
    private final List<Integer> winningNumbers;

    public Winning(String inputWinningNumbers) {
        this.winningNumbers = winningNumbersParsingForFieldValue(inputWinningNumbers);
    }

    private List<Integer> winningNumbersParsingForFieldValue(String inputWinningNumbers) {
        List<String> winningNumberList = Arrays.asList(inputWinningNumbers.split(DELIMITER));
        validate(inputWinningNumbers, winningNumberList);
        List<Integer> winningNumbers = new ArrayList<>(winningNumberList.stream().map(Integer::parseInt).toList());
        return winningNumbers;
    }

    private void validate(String winningNumbers, List<String> winningNumberList) {
        verifyFormatForInputValue(WINNINGS, winningNumbers);
        winningNumberList.forEach(number -> verifyFormatForInputValue(WINNING, number));
    }

    /*public List<Integer> getWinningNumbers() {
        return Collections.unmodifiableList(
                (List<Integer>)FiledMapper.getFieldValue(this, MethodProperty.WINNING_NUMBERS)
        );
    }*/

    public List<Integer> getWinningNumbers() {
        return Collections.unmodifiableList(winningNumbers);
    }
}
