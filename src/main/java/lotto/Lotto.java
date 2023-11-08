package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        for(Integer number:numbers){
            if(number<1 || number>45){
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
            if (Collections.frequency(numbers, number) > 1) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
            }
        }
    }

    public List<Integer> getNumbers(){
        List<Integer> copyNumbers = new ArrayList<Integer>();
        for(Integer copyNum : numbers){
            copyNumbers.add(copyNum);
        }
        Collections.sort(copyNumbers);
        return copyNumbers;
    }
}
