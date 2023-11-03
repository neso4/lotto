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
        if (isLottoNumbers(numbers)) {
            String message = String.format("로또 숫자의 범위는 %d~%d입니다.", LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX);
            throw new IllegalArgumentException(message);
        }
        if (LottoUtil.hasDuplicatedNumbers(numbers)) {
            throw new IllegalArgumentException("로또 번호에는 중복이 있을 수 없습니다");
        }
    }

    // TODO: 추가 기능 구현
    private boolean isLottoNumbers(List<Integer> numbers) {
        return numbers.stream().allMatch(this::isLottoNumber);
    }

    private boolean isLottoNumber(int number) {
        return LottoUtil.isInRange(number, LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX);
    }
}
