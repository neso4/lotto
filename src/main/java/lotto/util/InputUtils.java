package lotto.util;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

import java.util.List;

import static lotto.util.InputConvertUtils.lottoNumbersToIntegerList;
import static lotto.util.InputValidationUtils.*;

public class InputUtils {

    public static List<Integer> readLottoNumbers() {
        String lottoNumbers = readLine();
        validateBadDelimiterPosition(lottoNumbers);
        return lottoNumbersToIntegerList(lottoNumbers);
    }

    private static String readLine() {
        String input = Console.readLine();
        validateHasInput(input);
        return input;
    }

    public static LottoNumber readBonusNumber(Lotto winningLotto) {
        LottoNumber lottoNumber = new LottoNumber(readInt());
        validateDuplication(winningLotto, lottoNumber);
        return lottoNumber;
    }

    public static int readInt() {
        String input = Console.readLine();

        validateHasInput(input);
        validateIsIntFormat(input);

        return Integer.parseInt(input);
    }
}
