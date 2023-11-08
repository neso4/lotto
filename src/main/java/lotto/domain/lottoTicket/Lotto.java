package lotto.domain.lottoTicket;

import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import lotto.domain.exception.lottoException.LottoDuplicateException;
import lotto.domain.exception.lottoException.LottoNumberRangeException;
import lotto.domain.exception.lottoException.LottoSizeException;
import lotto.utils.NumberUtils;

public class Lotto implements Comparable<Lotto>, Iterable<Integer> {
    public static final int START_INCLUSIVE = 1;
    public static final int END_INCLUSIVE = 45;
    public static final int LOTTO_SIZE = 6;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateLottoSize(numbers);
        validateDuplicateNumber(numbers);
        validateRange(numbers);
    }

    private static void validateLottoSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new LottoSizeException();
        }
    }

    private void validateDuplicateNumber(List<Integer> numbers) {
        if (hasDuplicateNumber(numbers)) {
            throw new LottoDuplicateException();
        }
    }

    private boolean hasDuplicateNumber(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .toList()
                .size() < numbers.size();
    }

    private void validateRange(final List<Integer> numbers) {
        for (int number : numbers) {
            if (NumberUtils.isNotInRange(START_INCLUSIVE, END_INCLUSIVE, number)) {
                throw new LottoNumberRangeException();
            }
        }
    }

    public static Lotto of(final List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public boolean contains(final int number) {
        return numbers.contains(number);
    }

    public int match(Lotto otherLotto) {
        return (int) otherLotto.getNumbers()
                .stream()
                .filter(this::contains)
                .count();
    }

    @Override
    public int compareTo(Lotto other) {
        return IntStream.range(0, numbers.size())
                .map(i -> Integer.compare(numbers.get(i), other.numbers.get(i)))
                .filter(result -> result != 0)
                .findFirst()
                .orElse(0);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public Iterator<Integer> iterator() {
        return numbers.iterator();
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
