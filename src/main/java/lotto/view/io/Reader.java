package lotto.view.io;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lotto.constance.GameConst;
import lotto.exception.LottoGameException;

public class Reader {
    private static Pattern answerPattern = Pattern.compile(GameConst.FORMAT_INPUT_ANSWERS);

    public static int getMoney() {
        String inputMoney = Console.readLine();
        return parseInt(inputMoney);
    }

    public static List<Integer> getAnswerNumbers() {
        String input = Console.readLine().replaceAll(" ", "");
        validateAnswersFormat(input);
        return Arrays.stream(input.split(GameConst.DELIMITER))
                .map(Integer::parseInt)
                .toList();
    }

    public static Integer getBonusNumber() {
        String input = Console.readLine();
        return parseInt(input);
    }

    public static void validateAnswersFormat(String answerNumbers) {
        Matcher matcher = answerPattern.matcher(answerNumbers);
        if (!matcher.matches()) {
            throw LottoGameException.WRONG_ANSWERS_FORMAT.makeException();
        }
    }

    private static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw LottoGameException.WRONG_NUMBER_FORMAT.makeException();
        }
    }

}
