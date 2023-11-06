package lotto.utils.validator;

import static lotto.utils.constants.LottoConstants.INPUT_FORMAT;
import static lotto.utils.constants.LottoConstants.LOTTO_NUMBER_LENGTH;
import static lotto.utils.constants.LottoConstants.MAX_LOTTO_NUMBER;
import static lotto.utils.constants.LottoConstants.MIN_LOTTO_NUMBER;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Validator {

    public static void validBlank(String inputNumber) {
        if (inputNumber == null || inputNumber.isBlank()) {
            throw new IllegalArgumentException("공백이 아닌 숫자를 입력해주세요.\n");
        }
    }

    public static void validBlank(List<Integer> inputNumbers) {
        if (inputNumbers == null || inputNumbers.isEmpty()) {
            throw new IllegalArgumentException("공백이 아닌 당첨 번호를 입력해 주세요.\n");
        }
    }

    public static void validLength(List<Integer> inputNumbers) {
        if (inputNumbers.size() != LOTTO_NUMBER_LENGTH) {
            throw new IllegalArgumentException("6개의 당첨 번호를 입력해 주세요.\n");
        }
    }

    public static void validUniqueValue(List<Integer> inputNumbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(inputNumbers);
        if (uniqueNumbers.size() != inputNumbers.size()) {
            throw new IllegalArgumentException("중복 되지 않은 당첨 번호를 입력해주세요.\n");
        }
    }

    public static void validRange(List<Integer> inputNumbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(inputNumbers);
        if (uniqueNumbers.stream().anyMatch(number -> number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER)) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 숫자여야 합니다.\n");
        }
    }

    public static void validIsNumber(String inputNumber) {
        if (!isNumber(inputNumber)) {
            throw new IllegalArgumentException("숫자를 입력해주세요.\n");
        }
    }

    public static void validFormat(String inputNumber) {
        if (!isValidFormat(inputNumber)) {
            throw new IllegalArgumentException("숫자와 쉼표(,)만 입력 가능합니다.");
        }
    }

    private static boolean isNumber(String inputNumber) {
        return inputNumber.chars().allMatch(Character::isDigit);
    }

    private static boolean isValidFormat(String inputNumber) {
        return Pattern.matches(INPUT_FORMAT, inputNumber);
    }
}
