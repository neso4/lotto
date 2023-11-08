package util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.LottoPrinciples;

public class LottoNumber {

    public static List<Integer> makeLottoNumbers() {
        List<Integer> lottoNumberList = Randoms.pickUniqueNumbersInRange(
                LottoPrinciples.MIN_NUMBER.getNumber(),
                LottoPrinciples.MAX_NUMBER.getNumber(),
                LottoPrinciples.LIMIT_NUMBER.getNumber()
        );
        return lottoNumberList.stream()
                .sorted()
                .toList();
    }
}
