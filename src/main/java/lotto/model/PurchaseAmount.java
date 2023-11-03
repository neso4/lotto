package lotto.model;

import lotto.message.ExceptionMessage;

public class PurchaseAmount {
    private static final int PURCHASE_AMOUNT_UNIT = 1000;
    private static final String NUMBER_SEPARATOR = ",";
    private static final String EMPTY = "";
    private final int price;

    public PurchaseAmount(String price) {
        price = removeSeparator(price);
        validate(price);
        this.price = convertStringToInt(price);
    }

    public int getPurchaseAmount(){
        return price;
    }

    private void validate(String price) {
        IsNumber(price);
        IsPositive(convertStringToInt(price));
        IsMultipleOfUnit(convertStringToInt(price));
    }

    private void IsNumber(String price) {
        try {
            Integer.parseInt(price);
        } catch (NumberFormatException ex) {
            ExceptionMessage.INPUT_NOT_NUMBER_MESSAGE.throwException();
        }
    }

    private void IsPositive(int price) {
        if (price < 0) {
            ExceptionMessage.INPUT_ZERO_OR_LESS_MESSAGE.throwException();
        }
    }

    private void IsMultipleOfUnit(int price) {
        if (price % PURCHASE_AMOUNT_UNIT != 0) {
            ExceptionMessage.INPUT_NOT_MULTIPLE_OF_UNIT_MESSAGE.throwException(PURCHASE_AMOUNT_UNIT);
        }
    }

    private String removeSeparator(String price) {
        return price.replace(NUMBER_SEPARATOR, EMPTY);
    }

    private int convertStringToInt(String price) {
        return Integer.parseInt(price);
    }
}
