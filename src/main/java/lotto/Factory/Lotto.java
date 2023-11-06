package lotto.Factory;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    // 외부에서 Lotto 객체를 생성하는 것을 막기위해 private으로 변경
    private Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    // 정적 팩토리 메서드, 사용자 입력 로또 생성시 사용
    public static Lotto createLotto() {
        List<Integer> numbers = createRandomNumbers();
        return new Lotto(numbers);
    }

    // 테스트 및 정답로또 생성시 사용
    public static Lotto createLotto(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    private static List<Integer> createRandomNumbers(){
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        while (numbers.size() < 6) {
            putRandomNum(numbers);
        }
    }

    private void putRandomNum(List<Integer> numbers){
        int randomNumber = Randoms.pickNumberInRange(1, 45);
        if (!numbers.contains(randomNumber)) {
            numbers.add(randomNumber);
        }
    }

}

