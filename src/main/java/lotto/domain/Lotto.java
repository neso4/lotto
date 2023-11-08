package lotto.domain;

import java.util.List;
import lotto.exception.DuplicateBallNumberException;
import lotto.exception.InvalidLottoLengthException;

public class Lotto {
    public static final int LOTTO_BALL_COUNT = 6;

    private final List<LottoBall> numbers;

    public Lotto(List<LottoBall> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public boolean contains(LottoBall number) {
        return numbers.stream()
                .anyMatch(lottoBall -> lottoBall.equals(number));
    }

    public int getMatchedCount(Lotto lotto) {
        return (int) numbers.stream()
                .filter(lotto::contains)
                .count();
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoBall::getNumber)
                .toList();
    }

    private void validate(List<LottoBall> numbers) {
        validateLength(numbers);
        validateDuplicateNumber(numbers);
    }

    private void validateLength(List<LottoBall> numbers) {
        if (numbers.size() != LOTTO_BALL_COUNT) {
            throw new InvalidLottoLengthException();
        }
    }

    private void validateDuplicateNumber(List<LottoBall> numbers) {
        final long distinctCount = numbers.stream()
                .distinct()
                .count();

        if (distinctCount != LOTTO_BALL_COUNT) {
            throw new DuplicateBallNumberException();
        }
    }

}
