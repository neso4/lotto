package lotto.model;

import java.util.List;
import lotto.constant.ErrorMessage;
import lotto.constant.LottoConstants;

public class Lotto {    // 인스턴스 변수 추가 ㄴㄴ, 패키지 변경은 가능
    private final List<Integer> numbers;    // 접근 제어자 변경 ㄴㄴ

    public Lotto(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    public Integer get(int index) {
        return numbers.get(index);
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public int getMatchedCount(Lotto lotto) {
        int count = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.contains(lotto.get(i))) {
                count++;
            }
        }
        return count;
    }

    public void validateNumbers(List<Integer> numbers) {
        validateLottoTicketSize(numbers.size());
        numbers.forEach(this::validateNumberInRange);
        validateDuplication(numbers);
    }

    public void validateNumberInRange(int number) {
        if (number < LottoConstants.LOTTO_NUMBER_MIN || number > LottoConstants.LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_NOT_IN_RANGE_MESSAGE.getMessage());
        }
    }

    public void validateLottoTicketSize(int size) {
        if (size != LottoConstants.LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.THE_SIZE_OF_LOTTO_IS_NOT_PROPER_MESSAGE.getMessage());
        }
    }

    public void validateDuplication(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_MESSAGE.getMessage());
        }
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
