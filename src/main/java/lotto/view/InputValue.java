package lotto.view;

import lotto.exception.ErrorCode;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputValue {

    public static int getPurchasePrice() {

        String input = readLine();

        emptyValueCheck(input);

        int price = numberCheck(input);

        priceUnitCheck(price);

        return price;
    }

    private static void emptyValueCheck(String input) {
        if (input.length() == 0) {
            throw new IllegalArgumentException(ErrorCode.EMPTY_VALUE.getMessage());
        }
    }


    private static int numberCheck(String input) {

        if (!input.matches("\\d*")) {
            throw new IllegalArgumentException(ErrorCode.NOT_NUMBER.getMessage());
        }

        return Integer.parseInt(input);
    }

    private static void priceUnitCheck(int price) {
        if (price % 1000 != 0) {
            throw new IllegalArgumentException(ErrorCode.INCORRECT_UNIT.getMessage());
        }
    }

}
