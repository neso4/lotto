package lotto.validator;

import java.util.Arrays;
import java.util.List;
import lotto.utils.LottoUtil;

public class LottoValidator {
    private static final String REGEX_NUMBER = "^[0-9]+$";
    private static final int LEFT_BOUND = 1;
    private static final int RIGHT_BOUND = 45;

    public static void validateNumbers(String[] numbers) {
        Arrays.stream(numbers).forEach(LottoValidator::validateNumber);
    }

    public static void validateNumber(String number) {
        if (!isNumberFormat(number)) {
            throw new IllegalArgumentException(LottoUtil.ERROR_MESSAGE_PREFIX + LottoUtil.LOTTO_FORMAT);
        }

        if (isOutOfBoundsNumber(Integer.parseInt(number))) {
            throw new IllegalArgumentException(LottoUtil.ERROR_MESSAGE_PREFIX + LottoUtil.LOTTO_OUT_OF_BOUNDS);
        }
    }

    public static void validateBonusNumber(List<Integer> lottos, String number) {
        validateNumber(number);

        if (isBonusNumberDuplicate(lottos, Integer.parseInt(number))) {
            throw new IllegalArgumentException(LottoUtil.ERROR_MESSAGE_PREFIX + LottoUtil.LOTTO_DUPLICATE);
        }
    }

    private static boolean isBonusNumberDuplicate(List<Integer> lottos, int number) {
        return lottos.contains(number);
    }

    private static boolean isNumberFormat(String number) {
        return number.matches(REGEX_NUMBER);
    }

    private static boolean isOutOfBoundsNumber(int number) {
        return LEFT_BOUND > number || number > RIGHT_BOUND;
    }
}
