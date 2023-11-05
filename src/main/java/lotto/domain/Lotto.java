package lotto.domain;

import lotto.domain.constant.LottoConstant;
import lotto.exception.LottoException;
import lotto.exception.LottoExceptionMessage;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto implements LottoConstant {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        Collections.sort(numbers);
        this.numbers = numbers;
    }
    public List<Integer> getNumbers(){
        return numbers;
    }
    private void validate(List<Integer> numbers) {
        validateNumbers(numbers);

    }
    private void validateNumbers(List<Integer>numbers){
        validateNumbersSizeIsPickCount(numbers);
        validateNumberInRange(numbers);
    }

    private void validateNumbersSizeIsPickCount(List<Integer>numbers){
        if (numbers.size() != PICK_COUNT) {
            throw new LottoException(LottoExceptionMessage.INVALID_PICK_COUNT);
        }
    }

    private void validateNumberInRange(List<Integer>numbers){
        for (Integer number : numbers){
            if (number<MIN_NUMBER || number>MAX_NUMBER)
                throw new LottoException(LottoExceptionMessage.INVALID_NUMBER);
        }
    }
}

