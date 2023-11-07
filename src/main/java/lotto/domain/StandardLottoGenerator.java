package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StandardLottoGenerator {
    Set<Integer> numbers = new HashSet<>();
    public List<Integer> generateLottoNumbers() {
        while (numbers.size() < 6) {
            numbers.add(Randoms.pickNumberInRange(1, 45));
        }
        return new ArrayList<>(numbers);
    }
}
