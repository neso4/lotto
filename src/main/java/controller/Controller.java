package controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class Controller {
    private List<Integer> generateLottoNumbers() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return numbers;
    }
}
