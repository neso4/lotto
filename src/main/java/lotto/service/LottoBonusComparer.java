package lotto.service;

import java.util.List;
import lotto.message.LottoBonusErrorMessage;

public class LottoBonusComparer {
    public static void isValidBonusNumber(List<Integer> numbers, int inputNumber) {
        if (LottoBonusComparer.compare(numbers, inputNumber)) {
            throw new IllegalArgumentException(LottoBonusErrorMessage.DUPLICATE_LIST_ERROR.getMessage());
        }
    }

    public static boolean compare(List<Integer> numbers, int number) {
        return numbers.contains(number);
    }
}
