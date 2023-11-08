package lotto.utils;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class StringUtil {
    public static int divideByOneThousand(String number) {
        return Integer.parseInt(number) / 1000;
    }

    public static List<String> splitByCommas(String string) {
        return Arrays.stream(string.split(",")).toList();
    }

    public static String divideNumsByCommas(List<Integer> numbers) {
        StringBuilder stringBuilder =  new StringBuilder(getFirstNum(numbers));

        stringBuilder.append(getFirstNum(numbers));
        for (int i = 1; i < numbers.size(); i++) {
            stringBuilder.append(", ").append(numbers.get(i));
        }

        return stringBuilder.toString();
    }

    private static Integer getFirstNum(List<Integer> numbers) {
        return numbers.get(0);
    }

    public static String coverWithBrackets(String string) {
        String HEADER = "[";
        String FOOTER = "]";
        return HEADER + string + FOOTER;
    }

    public static String toKoreanWon(Long value) {
        NumberFormat koreanWonFormat = NumberFormat.getNumberInstance(Locale.KOREA);
        return koreanWonFormat.format(value);
    }
}
