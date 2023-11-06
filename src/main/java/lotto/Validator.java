package lotto;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lotto.Constant.*;
import static lotto.Message.*;

public class Validator {

    public static void validateNotEmptyInput(String input){
        if(input.isBlank()) throw new IllegalArgumentException(EMPTY_INPUT_ERROR);
    }

    public static int validateInputIsNumeric(String input){
        try{
            return Integer.parseInt(input);
        }catch (NumberFormatException exception){
            throw new IllegalArgumentException(NOT_VALID_NUMBER);
        }
    }

    public static void validatePositiveNumber(int amount){
        if(amount <= 0) throw new IllegalArgumentException(NOT_VALID_NUMBER);
    }

    public static void validateNotHasRemainder(int amount){
        if(amount % unitOfMoney != 0) throw new IllegalArgumentException(NOT_VALID_NUMBER);
    }

    public static List<Integer> validateSplittedInputLengthIsSix(String input){
        List<Integer> WinnerNumber = new ArrayList<>();

        String[] splittedInputs = input.split(",");
        validateLengthExceptBonus(splittedInputs);

        for(String splittedInput : splittedInputs){
            int number = validateInputIsNumeric(splittedInput);
            validatePositiveNumber(number);
            validateNumberInRange(number);
            WinnerNumber.add(number);
        }

        return WinnerNumber;
    }

    public static void validateComposedOfUniqueNumbers(List<Integer> numbers){
        long unique_number = numbers.stream().distinct().count();
        if(unique_number < numbers.size()){
            throw new IllegalArgumentException(NOT_COMPOSED_OF_UNIQUE_NUMBERS);
        }
    }

    private static void validateLengthExceptBonus(String... input){
        if(input.length != Constant.LottoLength-1) {
            throw new IllegalArgumentException(NOT_VALID_LENGTH);
        }
    }

    public static void validateNumberInRange(int number){
        if(number < minimumLottoNumber || number > maximumLottoNumber){
            throw new IllegalArgumentException(OUT_OF_RANGE_NUMBER);
        }
    }

}
