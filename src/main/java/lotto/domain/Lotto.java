package lotto.domain;

import static lotto.ErrorMessage.LOTTO_LENGTH_ERROR;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class Lotto {
    private static final int LOTTO_LENGTH = 6;

    private final Set<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        this(numbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toSet()));
        validateLength(numbers);
    }

    private void validateLength(List<Integer> numbers) {
        if (numbers.size() != LOTTO_LENGTH) {
            throw new IllegalArgumentException(String.format(LOTTO_LENGTH_ERROR.getMessage(), LOTTO_LENGTH));
        }
    }

    private Lotto(Set<LottoNumber> numbers) {
        validateDistinctLength(numbers);
        this.numbers = numbers;
    }

    private void validateDistinctLength(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_LENGTH) {
            throw new IllegalArgumentException(String.format(LOTTO_LENGTH_ERROR.getMessage(), LOTTO_LENGTH));
        }
    }

    public static Lotto createLotto(NumbersCreator numbersCreator) {
        return new Lotto(Set.copyOf(createLottoNumbers(numbersCreator)));
    }

    private static List<LottoNumber> createLottoNumbers(NumbersCreator numbersCreator) {
        return LottoNumber.createLottoNumbers(numbersCreator, LOTTO_LENGTH);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public int countMatchNumbers(Lotto lotto) {
        return (int) numbers.stream()
                .filter(lotto::contains)
                .count();
    }

    public List<Integer> getSortedNumbers() {
        return numbers.stream()
                .sorted()
                .map(LottoNumber::getNumber)
                .toList();
    }
}
