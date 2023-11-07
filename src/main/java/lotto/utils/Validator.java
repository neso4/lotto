package lotto.utils;

import java.util.List;

import static lotto.constValue.ConstMessage.*;
import static lotto.constValue.ConstNumber.*;

public class Validator {
    public static void validateNullOrEmpty(String input) {
        if (input == null || input.isEmpty()){
            System.out.println(NULL_ERROR_MESSAGE);
            throw new IllegalArgumentException();
        }
    }

    public static void validateNumbersLength(List<Integer> inputs) {
        if (inputs.size() != LOTTO_NUMBER_LENGTH) {
            System.out.println(LENGTH_ERROR_MESSAGE);
            throw new IllegalArgumentException();
        }
    }

    public static void validatePriceRange(String input) {
        if (Integer.parseInt(input) < LOTTO_PRICE ){
            System.out.println(PRICE_ERROR_MESSAGE);
            throw new IllegalArgumentException();
        }
    }

    public static void validatePriceModIsZero(String input) {
        if (Integer.parseInt(input) % LOTTO_PRICE != 0 ){
            System.out.println(MOD_ERROR_MESSAGE);
            throw new IllegalArgumentException();
        }
    }

    public static void validateIsNumber(String input){
        if(!isNumber(input)){
            System.out.println(NUMBER_ERROR_MESSAGE);
            throw new IllegalArgumentException();
        }
    }

    public static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static void validateIsNumbers(String[] inputs){
        for(String input: inputs){
            validateIsNumber(input);
        }
    }

    public static void validateAscending(List<Integer> numbers){
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) > numbers.get(i + 1)) {
                System.out.println(SORT_ERROR_MESSAGE);
                throw new IllegalArgumentException();
            }
        }
    }

    public static void validateIsNumberInRange(int winningNumber){
        if ( !(winningNumber >= MIX_LOTTO_NUMBER && winningNumber <= MAX_LOTTO_NUMBER) ){
            System.out.println(INPUT_ERROR_MESSAGE);
            throw new IllegalArgumentException();
        }
    }

    public static void validateIsNumbersInRange(List<Integer> winningNumbers){
        for (int winningNumber : winningNumbers){
            validateIsNumberInRange(winningNumber);
        }
    }

    public static void hasDuplicateInNumbers(List<Integer> winningNumbers){
        boolean hasDuplicate = winningNumbers.stream()
                .distinct()
                .count() != winningNumbers.size();

        if ( hasDuplicate ) {
            System.out.println(DUPLICATE_ERROR_MESSAGE);
            throw new IllegalArgumentException();
        }
    }
}
