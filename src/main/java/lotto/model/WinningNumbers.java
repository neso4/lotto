package lotto.model;

import java.util.ArrayList;
import java.util.List;
import lotto.util.Validation;
import lotto.view.InputView;

public class WinningNumbers {

    private final List<Integer> numbers;

    public WinningNumbers() {
        this.numbers = new ArrayList<>();
    }

    public Lotto getLotto(String input) {
        for (String token : input.split(",")) {
            int oneNum = validate(token);
            numbers.add(oneNum);
        }
        return new Lotto(numbers);
    }

    public int validate(String input) {
        int oneNum = Validation.validateInteger(input);
        Validation.validateNumberRange(oneNum);
        return oneNum;
    }

    public void clearList() {
        numbers.clear();
    }
}