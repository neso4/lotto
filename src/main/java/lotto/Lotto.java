package lotto;

import java.util.List;

public class Lotto {
    public static final int LOTTO_NUMBER_MIN = 1;
    public static final int LOTTO_NUMBER_MAX = 45;
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
    private boolean isLottoNumber(int number) {
        return LottoUtil.isInRange(number, LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX);
    }
}
