package lotto;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public void printLottoNumbers() {
        System.out.print("[");
        for(int number : numbers) {
            System.out.print(number + ", ");
        }
        System.out.println("]");
    }

    public int getMatchCount(List<Integer> winningNumbers) {
        boolean[] checkBox = makeCheckBox();
        return (int)winningNumbers.stream()
                .filter(number -> checkBox[number])
                .count();
    }

    public boolean checkBonusNumber(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    private boolean[] makeCheckBox() {
        boolean[] checkBox = new boolean[46];
        numbers.forEach(number -> checkBox[number] = true);
        return checkBox;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(Error.LOTTO_NUMBER_OUT_OF_COUNT_ERROR.getMessage());
        }
        if (numbers.size() > numbers.stream().distinct().count()) {
            throw new IllegalArgumentException(Error.LOTTO_NUMBER_DUPLICATE_ERROR.getMessage());
        }
        if (numbers.stream().anyMatch(number -> number < 1 || number > 45))
            throw new IllegalArgumentException(Error.LOTTO_NUMBER_OUT_OF_RANGE_ERROR.getMessage());
    }
}
