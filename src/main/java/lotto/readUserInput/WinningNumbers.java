package lotto.readUserInput;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import lotto.validation.ErrorMessage;
import lotto.validation.ErrorValidation;

public class WinningNumbers implements UserInput {
    public static List<Integer> numbers = new ArrayList<>();

    public void read() {
        String[] numbers = separateUserInput(userInput());
        addWinningNumbers(numbers);
    }

    public String userInput() {
        String readWinningNumbers = Console.readLine();
        try {
            ErrorValidation.isInputContainComma(readWinningNumbers);
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorMessage.ERROR_WINNING_CONTAINS_COMMA.getMessage());
            readWinningNumbers = userInput();
        }
        return readWinningNumbers;
    }

    public String[] separateUserInput(String inputNumbers) {
        return inputNumbers.split(",");
    }

    public int singleTypeConversion(String readSingleNumber) {
        return Integer.parseInt(readSingleNumber);
    }

    public void addWinningNumbers(String[] readNumbers) {
        String[] readInputNumber = readNumbers;
        while(numbers.size() < 6) { // 상수로 표현 가능.
            int singleNumber = singleTypeConversion(readInputNumber[numbers.size()]);
            try {
                ErrorValidation.isNumberInRange(singleNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(ErrorMessage.ERROR_WINNING_IN_RANGE.getMessage());
                numbers.clear();
                readInputNumber = separateUserInput(userInput());
            }
            numbers.add(singleNumber);
        }
    }




}
