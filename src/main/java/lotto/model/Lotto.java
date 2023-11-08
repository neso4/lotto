package lotto.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    public static final int PRICE = 1000;
    public static final int LOTTO_MIN = 1;
    public static final int LOTTO_MAX = 45;
    public static final int LOTTO_SIZE = 6;
    public static final String OPEN_BRACKET = "[";
    public static final String CLOSE_BRACKET = "]";
    public static final String INVALID_SIZE_ERROR = "로또 번호는 " + LOTTO_SIZE + "자리여야 합니다.";
    public static final String OUT_OF_RANGE_ERROR =
            "로또 번호는 " + LOTTO_MIN + "부터 " + LOTTO_MAX + " 사이의 숫자여야 합니다.";
    public static final String DUPLICATE_NUMBER_ERROR = "로또 번호는 중복되지 않아야 합니다.";

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(INVALID_SIZE_ERROR);
        }
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateInRange(numbers);
        validateUnique(numbers);
    }

    private void validateInRange(List<Integer> numbers) {
        numbers.forEach(number -> {
            if (isOutOfRange(number)) {
                throw new IllegalArgumentException(OUT_OF_RANGE_ERROR);
            }
        });
    }

    private boolean isOutOfRange(Integer number) {
        return number < LOTTO_MIN || number > LOTTO_MAX;
    }

    private void validateUnique(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);

        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER_ERROR);
        }
    }

    public long countMatchedNumbers(Lotto winningNumber) {
        return numbers.stream()
                .filter(winningNumber.numbers::contains)
                .count();
    }

    public boolean isBonusMatch(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public String getPurchaseLotto() {

        String purchaseLotto = numbers.stream()
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        return OPEN_BRACKET + purchaseLotto + CLOSE_BRACKET;
    }
}
