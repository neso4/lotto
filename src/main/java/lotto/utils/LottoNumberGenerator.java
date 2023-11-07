package lotto.utils;

import static lotto.enums.Constants.*;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoNumberGenerator {

    public static String getRandomNumber(){
        List<Integer> lottoNumbers = Randoms.pickUniqueNumbersInRange(MIN_INCLUSIVE.getValue(), MAX_INCLUSIVE.getValue(), LOTTO_NUMBER_SIZE.getValue());
        return lottoNumbers.toString().replaceAll("[\\['\\]]","");
    }
}
