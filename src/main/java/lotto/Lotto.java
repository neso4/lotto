package lotto;

import java.util.List;

public class Lotto {
    public static final int NUMBER_COUNT = 6;
    public static final int PRICE = 1000;
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        validateDuplicate(numbers);

        this.numbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != NUMBER_COUNT) {
            throw new IllegalArgumentException(String.format(ErrorMessage.MISMATCH_LOTTO_INPUT_FORMAT, NUMBER_COUNT));
        }
    }

    // TODO: 추가 기능 구현
    private void validateDuplicate(List<Integer> numbers) {
        int distinctSize = (int) numbers.stream()
                .distinct()
                .count();
        if (numbers.size() != distinctSize) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_NUMBER);
        }
    }

    public int compareLottoNumber(Lotto lotto) {
        int count = 0;
        for (LottoNumber lottoNumber : lotto.numbers) {
            if (containLottoNumber(lottoNumber)) {
                count++;
            }
        }
        return count;
    }

    public boolean containLottoNumber(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    @Override
    public String toString() {
        List<LottoNumber> sortList = numbers.stream()
                .sorted()
                .toList();
        StringBuilder result = new StringBuilder("[");
        for (LottoNumber lottoNumber : sortList) {
            result.append(lottoNumber).append(", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }
}
