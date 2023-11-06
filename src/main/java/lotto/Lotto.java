package lotto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int LOTTO_LENGTH = 6;
    public static final int LOTTO_PRICE = 1000;
    private final List<Integer> numbers;

    public List<Integer> getNumbers() {
        return numbers;
    }

    public Lotto(List<Integer> numbers) {
        validateSixNumberCount(numbers);
        validateOverlapNumber(numbers);
        validateNumberRange(numbers);
        this.numbers = sortingAscendingNumbers(numbers);
    }

    public static void validateSingleNumberRange(Integer number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1 ~ 45 사이로 발급되어야 합니다.");
        }
    }

    public int countCorrectLottoNumber(List<Integer> prizeNumbers) {
        int correctNumberCount = 0;

        for (Integer number : numbers) {
            if (prizeNumbers.contains(number)) {
                correctNumberCount++;
            }
        }

        return correctNumberCount;
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    private List<Integer> sortingAscendingNumbers(List<Integer> numbers) {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        sortedNumbers.sort(Comparator.naturalOrder());
        return sortedNumbers;
    }

    private void validateSixNumberCount(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개 입력이 필요합니다.");
        }
    }

    private void validateOverlapNumber(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (Integer number : numbers) {
            if (!uniqueNumbers.add(number)) {
                // contain 바꾸기
                throw new IllegalArgumentException("중복된 번호를 입력 하였습니다.");
            }
        }
    }

    private void validateNumberRange(List<Integer> numbers) {
        numbers.forEach(Lotto::validateSingleNumberRange);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}