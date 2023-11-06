package lotto.model;

import java.util.List;
import java.util.stream.IntStream;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    // TODO: 추가 기능 구현

    public int countSameNumber(WinningNumber winningNumber){
        int count = 0;
        for(int i=0; i<6; i++){
            if(winningNumber.answer.numbers.contains(numbers.get(i))){
                count++;
            }
        }
        return count;
    }
    @Override
    public boolean equals(Object obj){
        Lotto lotto = (Lotto) obj;
        return IntStream.range(0, 6).allMatch(i-> numbers.get(i).equals(lotto.numbers.get(i)));
    }

    public boolean isHitBonusNumber(BonusNumber bonusNumber) {
        return numbers.contains(bonusNumber.getNumber());
    }
}
