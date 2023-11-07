package lotto.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lotto.view.OutputView;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    // TODO: 추가 기능 구현

    public void printNaturalOrder() {
        List<Integer> naturalOrderNumbers = new ArrayList<>(getNumbers());
        naturalOrderNumbers.sort(Comparator.naturalOrder());
        OutputView.printMessage(naturalOrderNumbers.toString());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
