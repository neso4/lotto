package lotto.utils;

import java.util.List;

public class ParseUtil {
    public static List<Integer> parseNumbers(String numbers) {
        return StringUtil.splitByCommas(numbers).stream()
                .map(Integer::parseInt)
                .toList();
    }

    public static int parseNumber(String number) {
        return Integer.parseInt(number);
    }
}
