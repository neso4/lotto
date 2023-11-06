package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.validate.LottoValidator;

import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        try{
            LottoValidator.validateLottoNumber(numbers);
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        this.numbers = numbers;
    }

    public static Lotto generateRandomLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        Collections.sort(numbers);
        return new Lotto(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
