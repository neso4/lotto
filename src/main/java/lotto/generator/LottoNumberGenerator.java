package lotto.generator;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class LottoNumberGenerator {
    public List NumberGenerator(){
        List<Integer> LottoNumber = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        System.out.println(LottoNumber);
        return LottoNumber;
    }
}
