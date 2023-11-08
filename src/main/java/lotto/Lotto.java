package lotto;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        outOfRange(numbers);
        duplicatedWinningNum(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[Error] 당첨 번호는 6개의 숫자를 입력해야 합니다.");
        }
    }

    // TODO: 추가 기능 구현
    public void duplicatedBonusNum(int bonus) {
        if (numbers.contains(bonus)) {
            throw new IllegalArgumentException("[Error] 당첨 번호와 중복되는 숫자를 입력하셨습니다.");
        }
    }

    public void duplicatedWinningNum(List<Integer> numbers) {
        List<Integer> notDuplicateNumber = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (notDuplicateNumber.contains(numbers.get((i)))) {
                throw new IllegalArgumentException("[Error] 중복된 숫자를 입력하셨습니다.");
            }
            notDuplicateNumber.add(numbers.get(i));
        }
    }

    public void outOfRange(List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) < 1 || numbers.get(i) > 45) {
                throw new IllegalArgumentException("[Error] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }
    }
}
