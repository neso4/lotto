package lotto.domain;

import lotto.Constant.ErrorMessageConstant;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        sortNumbers(numbers);
        this.numbers = sortNumbers(numbers);
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }

    private void validate(List<Integer> numbers) {
        checkNumOfLotto(numbers);
        checkDuplicatedNumber(numbers);
        checkRangeOfLotto(numbers);

    }

    public void checkNumOfLotto(List<Integer> numbers){
        if(numbers.size() != 6){
            throw new IllegalArgumentException(ErrorMessageConstant.ONLY_LENGTH_SIX);
        }
    }

    private void checkRangeOfLotto(List<Integer> numbers) {
        if(numbers.stream().anyMatch(x -> x < 1 || x > 45)){
            throw new IllegalArgumentException(ErrorMessageConstant.BETWEEN_1_AND_45);
        }
    }

    private void checkDuplicatedNumber(List<Integer> numbers){
        if(numbers.stream().distinct().count() != 6){
            throw new IllegalArgumentException(ErrorMessageConstant.DUPLICATE);
        }
    }

    public List<Integer> sortNumbers(List<Integer> numbers){
        Arrays.sort(numbers.toArray());
        return numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public LottoValue match(Lotto winningLotto, int bonusNum) {
        int count = 0;
        for (Integer number : this.numbers) {
            if (winningLotto.getNumbers().contains(number)) {
                count++;
            }
        }
        if (count == 5 && this.numbers.contains(bonusNum)) {
            return LottoValue.FIVE_BONUS;
        }
        return LottoValue.getLottoValue(count);
    }
}
