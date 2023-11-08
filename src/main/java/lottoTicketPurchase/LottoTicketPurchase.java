package lottoTicketPurchase;

import static constant.ErrorMessage.NOT_DIVISIBLE_BY_1000;
import static constant.ErrorMessage.NOT_POSITIVE_NUMBER;
import static constant.RequestMessage.PURCHASE_AMOUNT_REQUEST_MESSAGE;

import base.Converter;
import camp.nextstep.edu.missionutils.Console;

public class LottoTicketPurchase {
    Converter converter = new Converter();

    public int getPurchaseAmount() {
        while (true) {
            try {
                return validateNumber();
            } catch (IllegalArgumentException error) {
                System.out.println(NOT_DIVISIBLE_BY_1000);
            }
        }
    }

    private int validateNumber() {
        int number = validateString();
        if (number % 1000 == 0) {
            return number;
        }
        throw new IllegalArgumentException();
    }

    private int validateString() {
        while (true) {
            String purchaseAmountString = receivePurchaseAmountString();
            final String REGEX = "[1-9][0-9]+";

            if (purchaseAmountString.matches(REGEX)) {
                return converter.stringToInteger(purchaseAmountString);
            }
            System.out.println(NOT_POSITIVE_NUMBER);
        }
    }

    private String receivePurchaseAmountString() {
        printPurchaseAmountRequestMessage();
        return Console.readLine();
    }

    private void printPurchaseAmountRequestMessage() {
        System.out.println(PURCHASE_AMOUNT_REQUEST_MESSAGE);
    }
}