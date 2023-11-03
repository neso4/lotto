package lotto.model;

import java.util.List;
import java.util.Set;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private final List<LottoNo> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
                .map(LottoNo::new)
                .toList();
    }

    private void validate(List<Integer> numbers) {
        if (isWrongSize(numbers)) {
            throw new IllegalArgumentException("[ERROR] 로또는 6개의 숫자로 이루어져야 합니다.");
        }

        if (isDuplicated(numbers)) {
            throw new IllegalArgumentException("[ERROR] 로또 숫자는 중복되지 않아야 합니다.");
        }
    }

    private boolean isWrongSize(List<Integer> numbers) {
        return numbers.size() != LOTTO_SIZE;
    }

    private boolean isDuplicated(List<Integer> numbers) {
        return Set.copyOf(numbers)
                .size() != LOTTO_SIZE;
    }

    public boolean isAnswerLotto() {
        return false;
    }

    public boolean isContained(LottoNo number) {
        return numbers.contains(number);
    }
}
