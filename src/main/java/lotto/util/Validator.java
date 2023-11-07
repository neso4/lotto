package lotto.util;

import static lotto.Constants.MAX_LOTTO_SIZE;
import static lotto.Constants.MAX_PRIZE_SIZE;
import static lotto.Constants.MIN_PRIZE_SIZE;
import static lotto.Constants.PURCHASE_AMOUNT_REGEX;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lotto.exception.ExceptionMessages;
import lotto.model.number.LottoNumber;

public class Validator {
    public static void validatePurchaseAmount(int amount) {
        Pattern pattern = Pattern.compile(PURCHASE_AMOUNT_REGEX);
        Matcher matcher = pattern.matcher(String.valueOf(amount));
        if (!matcher.matches()) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PURCHASE_AMOUNT_MESSAGE);
        }
    }

    /**
     * Description: Validate LottoNumbers<br> rule1: LottoNumbers must have 6 numbers<br> rule2: LottoNumbers must have
     * unique numbers<br>
     *
     * @param numbers
     * @return void
     * @throws IllegalArgumentException
     */
    public static void validateLottoNumbers(List<LottoNumber> numbers) {
        if (isLotteryNumberSizeValid(numbers)) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_LOTTO_SIZE_MESSAGE);
        }
        if (isLottoNumberDuplicate(numbers)) {
            throw new IllegalArgumentException(ExceptionMessages.DUPLICATE_LOTTO_MESSAGE);
        }
        numbers.forEach(number -> validateLottoNumber(number.getNumber()));
    }

    /**
     * Description: Validate LottoNumber<br> rule1: LottoNumber must be between 1 and 45<br>
     *
     * @param number
     * @return void
     * @throws IllegalArgumentException
     */
    public static void validateLottoNumber(int number) {
        if (number < LottoNumber.ONE.getNumber() || number > LottoNumber.FORTY_FIVE.getNumber()) {
            throw new IllegalArgumentException(ExceptionMessages.OUT_OF_RANGE_LOTTO_NUMBER_MESSAGE);
        }
    }

    /**
     * Description: Validate LottoNumbers<br> rule1: matchCount must be between 3 and 6<br>
     *
     * @param matchCount
     * @throws IllegalArgumentException
     */
    public static void validateMatchCount(int matchCount) {
        if (matchCount < MIN_PRIZE_SIZE || matchCount > MAX_PRIZE_SIZE) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MATCH_COUNT_MESSAGE);
        }
    }

    /**
     * Description: Validate LottoNumbers<br> rule1: LottoNumbers must have 6 numbers
     *
     * @param numbers
     * @return boolean
     */
    private static boolean isLotteryNumberSizeValid(List<LottoNumber> numbers) {
        return numbers.size() != MAX_LOTTO_SIZE;
    }

    /**
     * Description: Validate LottoNumbers<br> rule2: LottoNumbers must have unique numbers
     *
     * @param numbers
     * @return boolean
     */
    private static boolean isLottoNumberDuplicate(List<LottoNumber> numbers) {
        return numbers.stream().distinct().count() != numbers.size();
    }
}
