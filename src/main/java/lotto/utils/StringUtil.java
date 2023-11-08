package lotto.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtil {
    public static int divideByOneThousand(String number) {
        return Integer.parseInt(number) / 1000;
    }


    public static List<String> splitByCommas(String string) {
        return Arrays.stream(string.split(",")).toList();
    }
}
