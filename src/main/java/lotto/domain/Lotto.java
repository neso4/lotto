package lotto.domain;

import lotto.exception.lotto.LottoNumberDuplicateException;
import lotto.exception.lotto.LottoNumberRangeException;
import lotto.exception.lotto.LottoNumberSizeException;

import java.util.*;

public class Lotto {

    private static final int LOTTO_NUMBER_SIZE = 6;
    private static final int LOTTO_MINIMUM_VALUE = 1;
    private static final int LOTTO_MAXIMUM_VALUE = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumberSize(numbers);
        validateNumberLength(numbers);
        validateDuplicateNumber(numbers);
        this.numbers = numbers;
    }

    private void validateNumberSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw new LottoNumberSizeException();
        }
    }

    private void validateDuplicateNumber(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>();

        for (Integer number : numbers) {
            if (!uniqueNumbers.add(number)) {
                throw new LottoNumberDuplicateException();
            }
        }
    }

    private void validateNumberLength(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < LOTTO_MINIMUM_VALUE || number > LOTTO_MAXIMUM_VALUE) {
                throw new LottoNumberRangeException();
            }
        }
    }

    public int countCorrectLottoNumbers(Lotto answerLotto) {
        return (int) numbers.stream()
                .filter(answerLotto::contains)
                .count();
    }

    public boolean contains(int lottoNumber) {
        return this.numbers.contains((lottoNumber));
    }

    public boolean containsBonusNumber(int bonusNumber) {
        return this.numbers.contains(bonusNumber);
    }

    @Override
    public String toString() {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        return sortedNumbers.toString();
    }
}
