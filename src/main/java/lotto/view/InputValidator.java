package lotto.view;

public class InputValidator {

    private static final String EMPTY_EXCEPTION = "값을 입력해주세요.";
    private static final String DIGIT_EXCEPTION = "숫자를 입력해주세요.";
    private static final String DIGIT_DELIMITER_EXCEPTION = "숫자와 %s로 입력해주세요.";
    private static final String DIGIT_SPACE_REGEX = "[0-9 ]";
    private static final String DELIMITER = ",";
    private static final String EMPTY = "";

    public String validateNotEmpty(String inputValue) {
        if (inputValue.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_EXCEPTION);
        }
        return inputValue;
    }

    public String validateDigit(String inputValue) {
        if (0 < removeDigit(inputValue).length()) {
            throw new IllegalArgumentException(DIGIT_EXCEPTION);
        }
        return inputValue;
    }

    private String removeDigit(String inputValue) {
        return inputValue.replaceAll(DIGIT_SPACE_REGEX, EMPTY);
    }

    public String validateDigitAndDelimiter(String inputValue) {
        if (0 < removeDigitAndDelimiter(inputValue).length()) {
            throw new IllegalArgumentException(String.format(DIGIT_DELIMITER_EXCEPTION, DELIMITER));
        }
        return inputValue;
    }

    private String removeDigitAndDelimiter(String inputValue) {
        return removeDigit(inputValue).replace(DELIMITER, EMPTY);
    }
}
