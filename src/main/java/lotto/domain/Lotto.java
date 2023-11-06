package lotto.domain;

import lotto.constant.Errors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {

        validateLength(numbers);
        validateRange(numbers);
        validateDuplication(numbers);

        this.numbers = numbers;
    }

    public List<Integer> getNumbers(){
        return numbers;
    }

    private void validateLength(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(Errors.WINNING_NUMBER_WRONG_SIZE_MESSAGE.getErrorMessage());
        }
    }

    private void validateRange(List<Integer> numbers){
        for(int i : numbers){
            if (i < 1 || i > 45){
                throw new IllegalArgumentException(Errors.WINNING_NUMBER_WRONG_RANGE_MESSAGE.getErrorMessage());
            }
        }
    }

    private void validateDuplication(List<Integer> numbers){
        Set<Integer> set = new HashSet<>();
        for(Integer number : numbers){
            set.add(number);
        }
        if(set.size() != numbers.size()){
            throw new IllegalArgumentException(Errors.WINNIG_NUMBER_DUPLICATE_MESSAGE.getErrorMessage());
        }
    }

    // TODO: 추가 기능 구현
}
