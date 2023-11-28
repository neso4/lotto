package lotto.model;

import lotto.model.constants.LottoPrize;

import java.util.List;
import java.util.function.Predicate;

import static lotto.model.constants.ErrorMessage.DUPLICATION;
import static lotto.model.constants.ErrorMessage.OUTOFRANGE;
import static lotto.model.constants.LottoPrize.*;
public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        duplicate(numbers);
        this.numbers = numbers;
    }

    private void duplicate(List<Integer> numbers) {
        List<Integer> duplication = numbers.stream()
                .distinct()
                .toList();
        if(duplication.size()!=numbers.size()) {
            throw new IllegalArgumentException(DUPLICATION.getMessage());
        }
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(OUTOFRANGE.getMessage());
        }
    }
    public void checkSame(Integer bonusNumber, List<List<Integer>> lottoNumber) {
        for (List<Integer> integers : lottoNumber) {
            List<Integer> Result = integers.stream()
                    .filter(i -> this.numbers.stream().anyMatch(Predicate.isEqual(i)))
                    .toList();
            long bonusResult = integers.stream()
                    .filter(bonusNumber::equals).count();
            CheckPrize(Result.size(), bonusResult).addWinCount();

        }
    }
    public LottoPrize CheckPrize(int result, long bonusResult){
        if(result==6){
            return FIRST_PRIZE;
        }
        if(result==5&&bonusResult==1){
            return SECOND_PRIZE;
        }
        if(result==5&&bonusResult==0){
            return THIRD_PRIZE;
        }
        if(result==4){
            return FOURTH_PRIZE;
        }
        if(result==3){
            return FIFTH_PRIZE;
        }
        if(result<3){
            return LOOSE;
        }
        throw new IllegalArgumentException("존재 하지 않는 순위입니다.");
    }
}
