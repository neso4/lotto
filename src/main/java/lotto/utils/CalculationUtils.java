package lotto.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CalculationUtils {

    public static boolean isNumberInRange(int number, int start, int end) {
        return number >= start && number <= end;
    }

    public static boolean hasDuplicates(List<Integer> list) {
        Set<Integer> set = new HashSet<>(list);
        return set.size() < list.size();
    }

    public static boolean isDivisible(int target, int divisor){
        return target % divisor == 0;
    }

    public static double roundToDecimalPlace(double number, int location) {
        double multipleNum = Math.pow(10, location);
        return Math.round(number * multipleNum) / multipleNum;
    }

}
