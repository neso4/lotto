package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.constants.LottoStatus;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        checkRange(numbers);
        checkSameNumber(numbers);
        Collections.sort(numbers); // 로또 오름차순 정렬
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    // TODO: 추가 기능 구현
    private void checkRange(List<Integer> numbers) {
        if (numbers.stream().anyMatch(number ->
                number < LottoStatus.MIN_VALUE || number > LottoStatus.MAX_VALUE)) {
            throw new IllegalArgumentException();
        }
    }

    private void checkSameNumber(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != 6) {
            throw new IllegalArgumentException();
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public static List<Lotto> generate(int howManyLotto) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < howManyLotto; i++) {
            Lotto lotto = new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            lottos.add(lotto);
        }
        return lottos;
    }
}
