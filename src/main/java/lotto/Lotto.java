package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Lotto {
    private final List<Integer> numbers;

    public enum LottoConstants{
        LOTTO_PRICE(1000);
        private final int price;
        LottoConstants(int price){
            this.price = price;
        }
        public int getPrice(){
            return price;
        }
    }

    public Lotto(List<Integer> numbers) {
        isValidLottoNumbers(numbers);
        List<Integer> sortableNumbers = new ArrayList<>(numbers);
        Collections.sort(sortableNumbers);
        this.numbers = Collections.unmodifiableList(sortableNumbers);
    }


    private void isValidLottoNumbers(List<Integer> numbers) {
        if (numbers == null || numbers.size() != 6 || !numbers.stream().allMatch(num -> num >= 1 && num <= 45)) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
        if (numbers.stream().distinct().count() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public static Lotto generate() {
        return new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
    }
}
