package lotto.util.validator;

import static lotto.util.Constant.END_NUMBER;
import static lotto.util.Constant.LOTTO_LENGTH;
import static lotto.util.Constant.START_NUMBER;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.util.ExceptionMessage;

public class WinnerLottoValidator extends Validator {

    private final String SEPARATOR = ",";
    private final String REGEX = "^[0-9,]+$";
    private final String BLANK = " ";

    @Override
    public boolean validation(String input) {

        if (validationIsNumeric(input)) {
            System.out.println(ExceptionMessage.INVALID_INPUT_RANGE.getMessage());
            return false;
        }
        if (validationInputLength(input)) {
            System.out.println(ExceptionMessage.INVALID_INPUT_LENGTH.getMessage());
            return false;
        }
        if (validationHasDuplicate(input)) {
            System.out.println(ExceptionMessage.INVALID_INPUT_DUPLICATE.getMessage());
            return false;
        }
        if (validationHasBlank(input)) {
            System.out.println(ExceptionMessage.INVALID_INPUT_HAS_BLAMK.getMessage());
            return false;
        }
        if(validationOutOfRange(input)){
            System.out.println(ExceptionMessage.INVALID_INPUT_RANGE.getMessage());
            return false;
        }
        return true;
    }

    private boolean validationOutOfRange(String input) {
        List<String> numbers = Arrays.stream(input.split(SEPARATOR)).toList();
        return numbers.stream()
                .anyMatch(this::isOutOfRange);
    }

    private boolean isOutOfRange(String number){
        return START_NUMBER > Integer.parseInt(number) || Integer.parseInt(number) > END_NUMBER;
    }

    private boolean validationHasDuplicate(String input) {
        return Arrays.stream(input.split(SEPARATOR))
                .distinct()
                .collect(Collectors.toList())
                .size() != LOTTO_LENGTH;
    }

    private boolean validationIsNumeric(String input) {
        return !input.matches(REGEX);
    }

    private boolean validationHasBlank(String input) {
        return input.contains(BLANK);
    }

    private boolean validationInputLength(String input) {
        List<Integer> numbers = Arrays.stream(input.split(SEPARATOR))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return numbers.size() != LOTTO_LENGTH;
    }


}
