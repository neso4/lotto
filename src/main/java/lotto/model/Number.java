package lotto.model;

public class Number {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final String ERROR_NUMBER_RANGE = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    private final int number;

    public Number(int number) {
        validateNumber(number);
        this.number = number;
    }

    private static void validateNumber(int number) {
        if (number > MAX || number < MIN) {
            throw new IllegalArgumentException(ERROR_NUMBER_RANGE);
        }
    }

}
