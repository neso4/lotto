package lotto.utils;

import java.util.List;

public class ValidationUtil {

    public static final int MIN_POSSIBLE_LOTTO_NUMBER = 1;
    public static final int MAX_POSSIBLE_LOTTO_NUMBER = 45;

    public static void validateIsMoneyThousandUnit(String number) {
        if (Integer.parseInt(number) % 1000 != 0) {
            throw new IllegalArgumentException("로또 구입 금액은 1000원 단위여야 합니다.");
        }
    }

    public static void validateIsMoneyDigit(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("로또 구입 금액은 숫자여야 합니다.");
        }
    }

    public static void validateNoDuplicatedNumbers(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException("로또 번호는 서로 중복되지 않은 숫자여야 합니다.");
        }
    }

    public static void validateIsBonusNumberDuplicated(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("해당 번호는 보너스 번호로 사용할 수 없습니다. 이미 로또 번호 안에 존재합니다.");
        }
    }

    public static void validateHasSixNumbers(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호의 개수는 6개여야 합니다.");
        }
    }
    
    public static void validateIsAllDigit(List<String> inputs) {
        for (String input : inputs) {
            validateIsDigit(input);
        }
    }

    public static void validateIsDigit(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("로또 번호는 숫자여야 합니다.");
        }
    }

    public static void validateNumberInRange(int number) {
        if (number < MIN_POSSIBLE_LOTTO_NUMBER || number > MAX_POSSIBLE_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1과 45 사이여야 합니다.");
        }
    }
}
