package lotto.domain;

import java.util.List;

public class Lotto {
    private static final String SIZE_ERROR_MESSAGE = "[ERROR] 로또 번호는 6개를 입력해야 합니다.";
    private static final int LOTTO_COUNT = 6;
    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    private void validate(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_COUNT) {
            throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
        }
    }

    public List<Integer> getNumbers() {
        return this.numbers.stream().map(LottoNumber::getNumber).toList();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }
}