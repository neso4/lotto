package utils;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;
import validator.Validator;

public class Utils {
    public static List<Integer> convertToIntegerList(String[] input) {
        Validator.validateWinningNumbersIsNumeric(input);
        return Arrays.stream(input)
                     .map(Integer::parseInt)
                     .sorted()
                     .toList();
    }

    public static String[] splitInputByComma(String input) {
        return input.split("\\s*,\\s*");
    }

    public static List<Integer> generateRandomUniqueNumbers(
            int minValueInclusive, int maxValueInclusive, int selectionCount) {
        return Randoms.pickUniqueNumbersInRange(minValueInclusive, maxValueInclusive, selectionCount);
    }
}
