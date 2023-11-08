package lotto.Service;

import java.util.List;
import lotto.Enum.LottoResult;
import lotto.Lotto;

public class OutputService {


    private static final String LOTTO_OPENING_BRACKET = "[";
    private static final String LOTTO_CLOSING_BRACKET = "]";
    private static final String ERROR_MESSAGE = "[ERROR]";

    public static void askStatmement(String askStatement) {
        System.out.println(askStatement);
    }

    private static String getSingleLottoNumber(List<Integer> numbers, int idx) {
        String ret = "";

        ret = ret.concat(numbers.get(idx).toString());
        if (idx != numbers.size() - 1) {
            ret = ret.concat(", ");
        }

        return ret;
    }

    private static String makeLottoPrintStatement(List<Integer> sortedNumbers) {
        String statement = LOTTO_OPENING_BRACKET;

        for (int number = 0; number < sortedNumbers.size(); number++) {
            statement = statement.concat(getSingleLottoNumber(sortedNumbers, number));
        }

        return statement.concat(LOTTO_CLOSING_BRACKET);
    }

    public static void printLottoNumbers(Lotto lotto) {
        List<Integer> sortedNumbers = lotto.getSortedNumbers();
        System.out.println(makeLottoPrintStatement(sortedNumbers));
    }

    public static void printErrorMessage(String message) {

        String errorMessage = ERROR_MESSAGE.concat(" " + message);
        System.out.println(errorMessage);
    }
}
