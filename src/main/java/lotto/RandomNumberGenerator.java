package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomNumberGenerator {
    public List<Integer> lottoNumberGenerate(){
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}
