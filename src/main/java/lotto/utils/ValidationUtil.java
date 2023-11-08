package lotto.utils;

import java.util.List;

public class ValidationUtil {
    public static void validateIsMoneyThousandUnit(String number) {
        if (Integer.parseInt(number) % 1000 != 0) throw new IllegalArgumentException("로또 구입 금액은 1000원 단위여야 합니다.");
    }

    public static void validateIsMoneyDigit(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("로또 구입 금액은 숫자여야 합니다.");
        }
    }

    public static void validateNoDuplicatedNumbers(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) throw new IllegalArgumentException("로또 번호는 서로 중복되지 않은 숫자여야 합니다.");
    }

    public static void validateHasSixNumbers(List<Integer> numbers) {
        if (numbers.size() != 6) throw new IllegalArgumentException("로또 번호의 개수는 6개여야 합니다.");
    }
}
