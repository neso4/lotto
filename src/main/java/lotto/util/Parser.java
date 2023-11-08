package lotto.util;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private static final String DELIMITER = ",";

    public static int parseSingleNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_NUMBER.getMessage());
        }
    }

    public static List<Integer> parseNumbers(String input) {
        String[] numbers = input.split(DELIMITER);
        return Arrays.stream(numbers)
                .map(String::trim)
                .map(Parser::parseSingleNumber)
                .toList();
    }
}
