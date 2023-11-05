package lotto.util;

import java.util.HashSet;
import java.util.List;
import lotto.enums.Message;

public class Exception {
    public static int checkInvalidNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Message.INVALID_NUMBER.getMessage());
        }
    }

    public static void checkDuplicationNumber(List<Integer> numbers) {
        HashSet<Integer> duplicationNumber = new HashSet<>(numbers);
        if (duplicationNumber.size() != numbers.size()) {
            throw new IllegalArgumentException(Message.DUPLICATION_NUMBER.getMessage());
        }
    }

    public static void checkUnitPaymentAmount(int paymentAmount) {
        if (paymentAmount % 1000 != 0) {
            throw new IllegalArgumentException(Message.INVALID_AMOUNT.getMessage());
        }
    }

    public static void checkRangePaymentAmount(int paymentAmount) {
        if (paymentAmount < 1000) {
            throw new IllegalArgumentException(Message.OUT_OF_RANGE_AMOUNT.getMessage());
        }
    }

    public static void checkRangeLottoNumber(List<Integer> numbers) {
        for (int number : numbers){
            if(number < 1 && number > 45){
                throw new IllegalArgumentException(Message.OUT_OF_RANGE_LOTTO.getMessage());
            }
        }
    }
}
