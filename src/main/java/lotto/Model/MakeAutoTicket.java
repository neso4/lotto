package lotto.Model;

import java.util.List;
import camp.nextstep.edu.missionutils.Randoms;

public class MakeAutoTicket {
    private List<Integer> numbers;

    public MakeAutoTicket() {
        numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }

    public List<Integer> getnumbers() {
        return numbers;
    }
}
