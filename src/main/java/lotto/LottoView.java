package lotto;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static lotto.constant.ExceptionMessage.ERROR_NOT_IN_SPLIT_WORD;
import static lotto.constant.ExceptionMessage.ERROR_NOT_NUMBER;

import java.util.ArrayList;
import java.util.List;

public class LottoView {

    int inputNumber(String message) {

        printMessage(message);
        String input = readLine();

        return getStringToInt(input);
    }

    void printMessage(String message) {
        System.out.println(message);
    }

    List<Integer> inputNumbers(String message) {

        printMessage(message);
        String input = readLine();

        return convertStringToList(input);
    }

    private List<Integer> convertStringToList(String input) {

        validateStringInSplitWord(input);

        String[] splitInput = input.split(",");

        List<Integer> numbers = new ArrayList<>();

        for (String word : splitInput) {
            numbers.add(getStringToInt(word));
        }

        return numbers;
    }

    private int getStringToInt(String input) {

        int number;

        try {
            number = Integer.parseInt(input);

        } catch (NumberFormatException numberFormatException) {
            throw new NumberFormatException(ERROR_NOT_NUMBER);
        }

        return number;
    }

    private void validateStringInSplitWord(String input) {
        if (!input.contains(",")) {
            throw new IllegalArgumentException(ERROR_NOT_IN_SPLIT_WORD);
        }
    }

}
