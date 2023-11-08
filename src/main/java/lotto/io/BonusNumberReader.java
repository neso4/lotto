package lotto.io;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.LottoNumberRangeException;
import lotto.exception.ValueException;

public class BonusNumberReader {
    public static int bonusnumber() {
        try {
            String bonusnumber = Console.readLine();
            ValueException.validateNumber(bonusnumber);
            int number = Integer.parseInt(bonusnumber);
            LottoNumberRangeException.NumberRangeException(number);
            return number;
        } catch (Exception e) {
            return bonusnumber(); // 예외가 발생한 경우 다시 입력하도록 재귀 호출
        }
    }
}
