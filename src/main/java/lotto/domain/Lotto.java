package lotto.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.utils.constants.ExceptionMessage;
import lotto.utils.constants.LottoNumConstant;

public class Lotto {
    private static final String COMMA = ",";
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ExceptionMessage.WINNING_LOTTO_NUMBER.getMessage());
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public static void validateIsBlank(String purchaseAmount) {
        if (purchaseAmount == null || purchaseAmount.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.INPUT_NUMBER_BLANK.getMessage());
        }
    }

    public static int validateInputPurchaseAmount(int purchaseAmount) {
        if (purchaseAmount < LottoNumConstant.LOTTO_AMOUNT_PURCHASE.getNumber() || purchaseAmount % LottoNumConstant.LOTTO_AMOUNT_PURCHASE.getNumber() != LottoNumConstant.ZERO.getNumber()) {
            throw new IllegalArgumentException(ExceptionMessage.PURCHASE_AMOUNT_NOT_THOUSAND.getMessage());
        }
        return purchaseAmount;
    }

    public static String validateInputString(String purchaseAmount) {
        if (!purchaseAmount.matches("\\d+")) {
            throw new IllegalArgumentException(ExceptionMessage.INPUT_STRING_NOT_NUMBER.getMessage());
        }
        return purchaseAmount;
    }

    public static List<Integer> validateInputWinningNumber(String winningNum) {
        List<String> numberStrings = Arrays.asList(winningNum.split(COMMA));
        validateMinimumSize(numberStrings);
        List<Integer> numberList = parseToIntegers(numberStrings);
        validateNoDuplicates(numberList);
        validateNumberRange(numberList);
        validateSixNumbers(numberList);
        return validateDuplicateBonusNumber(numberList);
    }

    public static void validateMinimumSize(List<String> numberStrings) {
        if (numberStrings.size() < LottoNumConstant.LOTTO_SIZE.getNumber()) {
            throw new IllegalArgumentException(ExceptionMessage.WINNING_LOTTO_NUMBER.getMessage());
        }
    }

    public static List<Integer> parseToIntegers(List<String> numberStrings) {
        return numberStrings.stream()
            .map(Integer::parseInt)
            .toList();
    }

    public static void validateNoDuplicates(List<Integer> numbers) {
        if (hasDuplicate(numbers)) {
            throw new IllegalArgumentException(ExceptionMessage.WINNING_LOTTO_DUPULICATED_NUMBER.getMessage());
        }
    }

    public static boolean hasDuplicate(List<Integer> numbers) {
        return numbers.stream().distinct().count() < numbers.size();
    }

    public static void validateNumberRange(List<Integer> numbers) {
        if (numbers.stream().anyMatch(num -> num < LottoNumConstant.LOTTO_MIN_NUM.getNumber() || num > LottoNumConstant.LOTTO_MAX_NUM.getNumber())) {
            throw new IllegalArgumentException(ExceptionMessage.WINNIG_LOTTO_INCLUDED_VALUE_NUMBER.getMessage());
        }
    }

    public static void validateSixNumbers(List<Integer> numbers) {
        if (numbers.size() != LottoNumConstant.LOTTO_SIZE.getNumber()) {
            throw new IllegalArgumentException(ExceptionMessage.WINNIG_LOTTO_SIX_VALUE_NUMBER.getMessage());
        }
    }

    public static List<Integer> validateDuplicateBonusNumber(List<Integer> numberList) {
        Set<Integer> numberSet = new HashSet<>(numberList);
        if (numberSet.size() != numberList.size()) {
            throw new IllegalArgumentException(ExceptionMessage.INPUT_LOTTO_NUM_DUPLICATED_BONUSNUM.getMessage());
        }
        return numberList;
    }

    public static int validateBonusNumberRange(int bonusNumber, Lotto winningNumbers) {
        if (bonusNumber < LottoNumConstant.LOTTO_MIN_NUM.getNumber() || bonusNumber > LottoNumConstant.LOTTO_MAX_NUM.getNumber()) {
            throw new IllegalArgumentException(ExceptionMessage.WINNIG_LOTTO_INCLUDED_VALUE_NUMBER.getMessage());
        }

        List<Integer> numbers = winningNumbers.getNumbers();
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ExceptionMessage.INPUT_LOTTO_NUM_DUPLICATED_BONUSNUM.getMessage());
        }
        return bonusNumber;
    }
    @Override
    public String toString() {
        return numbers.toString();
    }
}

