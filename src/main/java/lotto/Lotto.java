package lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lib.constant.LottoConstants;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.validateNumbers(numbers);
        this.numbers = this.sortNumbers(numbers);
    }

    private void validateNumbers(List<Integer> numbers) {
        this.validateNumberSize(numbers);
        this.validateNumberDuplicated(numbers);
        numbers.forEach(number -> this.validateNumberRange(number));
    }

    private void validateNumberSize(List<Integer> numbers) {
        if (numbers.size() != LottoConstants.LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개만 입력할 수 있습니다.");
        }
    }

    private void validateNumberDuplicated(List<Integer> numbers) {
        Set<Integer> uniqueNumber = new HashSet<Integer>();

        numbers.forEach(number -> uniqueNumber.add(number));

        if (uniqueNumber.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 로또 번호를 입력하였습니다.");
        }
    }

    private void validateNumberRange(Integer number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 로또 번호입니다.");
        }
    }
    

    private List<Integer> sortNumbers(List<Integer> numbers) {
        return numbers.stream().sorted().collect(Collectors.toList());
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }

    public List<Integer> getMatchedLotto(List<Integer> answer, int bonusWinnerNumber) {
        List<Integer> matchedLotto = new ArrayList<Integer>();

        for (int i = 0; i < LottoConstants.LOTTO_SIZE; ++i){
            if (answer.contains(this.numbers.get(i)) || this.numbers.get(i) == bonusWinnerNumber) {
                matchedLotto.add(this.numbers.get(i));
            }
        }

        return matchedLotto;
    }

    @Override
    public String toString() {
        return "[" + this.numbers.stream().map(String::valueOf).collect(Collectors.joining(", ")) + "]";
    }
}
