package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class NumberGenerator {
    public static List<Integer> formatNumbers(String input) {
        String[] numbers = input.split(",", -1);
        List<Integer> result = new ArrayList<>();

        for (String number : numbers) {
            result.add(formatNumber(number));
        }
        return result;
    }

    public static Integer formatNumber(String input) {
        validate(input);
        return Integer.valueOf(input);
    }


    private static void validate(String input) {
        if (!Pattern.matches("^[0-9]*$", input)) {
            throw new IllegalArgumentException("[Error] 입력은 숫자만 가능합니다.");
        }
    }
}
