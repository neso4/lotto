package lotto.model.validator;

import lotto.model.lotto.WinningNumber;

import java.util.List;
import java.util.regex.Pattern;

import static lotto.model.constants.IntegerConstants.*;
import static lotto.model.constants.RegexPatterns.*;
import static lotto.model.validator.ExceptionHandler.illegalArgument;
import static lotto.model.validator.ExceptionMessage.*;

public class InputValidator {
    private static final Pattern MULTIPLE_1000 = Pattern.compile(REGEX_MULTIPLE_1000.get());
    private static final Pattern NUMERIC = Pattern.compile(REGEX_NUMERIC.get());
    private static final Pattern NUMERIC_WITH_COMMA = Pattern.compile(REGEX_NUMERIC_WITH_COMMA.get());

    public static void numberMustBeMultipleOf1000(String input){
        if (MULTIPLE_1000.matcher(input).matches()) return;
        illegalArgument(PURCHASE_AMOUNT_MUST_BE_MULTIPLE_OF_1000.getMessage());
    }

    public static void lottoMakesSixNumbers(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != SIZE_OF_LOTTO.get()) {
            illegalArgument(LOTTO_INCLUDES_6_NUMBERS.getMessage());
        }
    }

    public static void lottoMustBeUniqueNumber(List<Integer> inputNumbers) {
        long inputNumberCount = inputNumbers.stream().distinct().count();
        if (inputNumbers.size() != inputNumberCount) {
            illegalArgument(LOTTO_INCLUDES_UNIQUE_NUMBERS.getMessage());
        }
    }

    public static void lottoMustBeUniqueNumber(WinningNumber winningNumber, Integer bonusNumber) {
        if (winningNumber.compareWinningNumberAndBonusNumber(bonusNumber)) {
            illegalArgument(BONUS_NUMBER_MUST_UNIQUE_WITH_WINNING_NUMBER.getMessage());
        }
    }

    public static void numberMustBe1To45(List<Integer> inputNumbers) {
        long count = inputNumbers.stream()
                .filter(InputValidator::isNumberValidRange)
                .count();
        if (count != inputNumbers.size()) {
            illegalArgument(LOTTO_RANGE_IS_1_TO_45.getMessage());
        }
    }

    private static boolean isNumberValidRange(int number){
        return (number >= MINIMUM_OF_LOTTO.get()) && (number <= MAXIMUM_OF_LOTTO.get());
    }

    public static void isInputEmpty(String input){
        if (input.isEmpty()){
            illegalArgument(EMPTY_INPUT.getMessage());
        }
    }

    public static void checkNumericInput(String input){
        if (NUMERIC.matcher(input).matches()) return;
        illegalArgument(INPUT_MUST_BE_NUMERIC.getMessage());
    }

    public static void checkNumericWithCommaInput(String input){
        if (NUMERIC_WITH_COMMA.matcher(input).matches()) return;
        illegalArgument(INPUT_MUST_BE_NUMERIC_WITH_COMMA.getMessage());
    }
}
