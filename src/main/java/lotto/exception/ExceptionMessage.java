package lotto.exception;

public enum ExceptionMessage {
    AMOUNT_NOT_DIVISIBLE("[ERROR] The amount is not divisible by the price of a single lottery ticket."),
    NEGATIVE_AMOUNT_NOT_ALLOWED("[ERROR] The purchase amount cannot be negative."),
    AMOUNT_CANNOT_BE_ZERO("[ERROR] The purchase amount cannot be zero."),
    AMOUNT_OUT_OF_BOUNDS("[ERROR] The purchase amount is out of bounds."),
    VALUE_CANNOT_BE_EMPTY_OR_BLANK("[ERROR] It cannot be blank or an empty value."),
    NUMERIC_VALUES_ONLY("[ERROR] Only numeric values are allowed."),
    INVALID_WINNING_NUMBER_FORMAT("[ERROR] Six winning numbers must be entered, separated by commas."),
    NUMBER_OUT_OF_RANGE("[ERROR] Value must be within the range."),
    NO_DUPLICATES_ALLOWED("[ERROR] Values must be unique and cannot be duplicated."),
    NON_ZERO_STARTING_POSITIVE_NUMBERS_REQUIRED(
            "[ERROR] Each winning number must be a positive number that does not start with 0.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
