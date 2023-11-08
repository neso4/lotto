package lotto.util;

import java.util.StringTokenizer;

public class NumberConverter {
    private static final String FRAC_POINT = ".";
    private static final String INTEGER_FORMAT = "\\B(?=(\\d{3})+(?!\\d))";

    public static String doubleToString(Double value, int cutSize) {
        String retStr = String.format("%." + cutSize + "f", value);
        StringTokenizer splittedValue = new StringTokenizer(retStr, FRAC_POINT);
        String intPart = splittedValue.nextToken();
        String fracPart = splittedValue.nextToken();
        intPart = intToString(Integer.parseInt(intPart));
        retStr = intPart + FRAC_POINT + fracPart;
        return retStr;
    }

    public static String intToString(Integer value) {
        String retStr = value.toString();
        retStr = retStr.replaceAll(INTEGER_FORMAT, ",");
        return retStr;
    }
}
