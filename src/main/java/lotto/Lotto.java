package lotto;

import java.util.Arrays;
import java.util.List;

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
    @Override
    public String toString() {
        return "Lotto{" +
                "numbers=" + numbers +
                '}';
    }

    public String length(Lotto lotto){
        List<Integer> list = lotto.numbers;
        int[] arr = new int[list.size()];

        for(int i=0; i<list.size(); i++){
            arr[i] = list.get(i);
        }

        return Arrays.toString(arr);
    }
}
