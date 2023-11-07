package lotto.domain;

import static lotto.domain.policy.LottoSizePolicy.LOTTO_SIZE;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        verifyDuplicatedNumber(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE.getSize()) {
            throw new IllegalArgumentException();
        }
    }

    // TODO: 추가 기능 구현
    private void verifyDuplicatedNumber(List<Integer> numbers) throws IllegalArgumentException {
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(i) == numbers.get(j)) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public int getSize() {
        return this.numbers.size();
    }


}
