package lotto.utils;

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
}
