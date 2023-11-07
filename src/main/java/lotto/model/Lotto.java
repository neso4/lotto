package lotto.model;

import static lotto.exception.ExceptionMessage.DUPLICATE_LOTTO_NUMBER;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int LOTTO_NUMBER_SIZE = 6;
    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        validate(numbers);
        this.numbers = sortLottoNumbers(numbers);
    }

    private void validate(final List<Integer> numbers) {
        final Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBER.format());
        }
    }

    private List<Integer> sortLottoNumbers(final List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .toList();
    }

    public boolean isContainsNumber(final int number) {
        return numbers.contains(number);
    }

    public long winningNumberCount(final Lotto winningLottoTicket) {
        return numbers.stream()
                .filter(winningLottoTicket.numbers::contains)
                .count();
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }
}
