package lotto.parsing;
import java.util.*;
import java.util.stream.Collectors;
import lotto.validation.NumberValidation;

public class Parsing {
    private static final String STRING_COMMA = ",";
    public static int stringToInt(String inputValue){
        return Integer.parseInt(inputValue);
    }

    public static int makeDivision(int input){
        NumberValidation.isDivisible(input);
        System.out.println();
        return input/1000;
    }

    public static List<Integer> makeList(String input){
        String [] splitString = splitByComma(input);
        NumberValidation.isCorrectNumber(splitString);
        return stringArrayToIntegerList(splitString);
    }

    public static String[] splitByComma(String input){
        NumberValidation.isCommaSplitter(input);
        return input.split(STRING_COMMA);
    }

    public static List<Integer> stringArrayToIntegerList(String[] input){
        return Arrays.stream(input).map(s->stringToInt(s)).collect(Collectors.toList());
    }

}
