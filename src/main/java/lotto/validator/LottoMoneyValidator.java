package lotto.validator;

import lotto.constant.ErrorMessages;

public class LottoMoneyValidator {
    public static void validate(String lottoMoneyInput) {
        isNumeric(lottoMoneyInput);
        isThousands(lottoMoneyInput);
        isOverThousands(lottoMoneyInput);
    }

    private static void isNumeric(String lottoMoneyInput) {
        try {
            Integer.parseInt(lottoMoneyInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessages.NOT_NUMBER.getMessage());
        }
    }

    private static void isThousands(String lottoMoneyInput) {
    }

    private static void isOverThousands(String lottoMoneyInput) {
    }

    private static void isTooBig(String lottoMoneyInput) {

    }
}
