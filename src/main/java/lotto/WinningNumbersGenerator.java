package lotto;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class WinningNumbersGenerator {
    private final int START_NUM = 1;
    private final int END_NUM = 45;
    private final int NUM_COUNT = 6;
    private final String DELIMITER = ",";

    private List<Integer> winningNumbers;
    private int bonusNumber;
    private String[] inputDividedByDelimiter;

    public void makeWinningNumbers(final String winningNumbersInput) {
        this.dividedInputByDelimiter(winningNumbersInput);
        this.validateInputDividedByDelimiter();
        this.validateDividedInput();

    }

    public void dividedInputByDelimiter(final String winningNumbersInput) {
        this.inputDividedByDelimiter = winningNumbersInput.split(DELIMITER);
    }

    public void validateInputDividedByDelimiter() {
        if (this.inputDividedByDelimiter.length != NUM_COUNT) {
            throw new IllegalArgumentException("[ERROR] 6개의 번호가 필요합니다.");
        }

        final Set<String> noDuplicatedInput = Arrays.stream(this.inputDividedByDelimiter)
                .collect(Collectors.toSet());
        if (noDuplicatedInput.size() != NUM_COUNT) {
            throw new IllegalArgumentException("[ERROR] 중복된 번호가 있습니다.");
        }
    }

    public void validateDividedInput() {
        for (String winningNumberInput : this.inputDividedByDelimiter) {
            if (winningNumberInput.chars().anyMatch(c -> !Character.isDigit((char) c))) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.");
            }
            int winningNumber = Integer.parseInt(winningNumberInput);
            if (winningNumber > END_NUM || winningNumber < START_NUM) {
                throw new IllegalArgumentException("[ERROR] 번호는 1부터 45사이의 숫자여야 합니다.");
            }
        }
    }

    public String[] getInputDividedByDelimiter() {
        return inputDividedByDelimiter;
    }
}
