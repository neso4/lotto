package lotto.validator;

import java.util.List;
public class WinningValidator {

    private static final int MIN_WINNING_NUMBER = 1;
    private static final int MAX_WINNING_NUMBER = 45;
    private static final int WINNING_SIZE = 6;

    public static void validate(List<Integer> winningList) {
        validateSize(winningList);
        validateRange(winningList);
        validateDuplicate(winningList);

    }
    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != WINNING_SIZE) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 반드시 6개를 골라야 합니다. ");
        }
    }

    private static void validateRange(List<Integer> numbers) {
        numbers.stream()
                .forEach(number -> validateRangeHelper(number));
    }

    private static void validateRangeHelper(int number) {
        if (number < MIN_WINNING_NUMBER || number > MAX_WINNING_NUMBER){
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 1 ~ 45 사이여야 합니다.");
        }
    }

    private static void validateDuplicate(List<Integer> numbers){
        long duplicateCount = numbers.stream()
                .distinct()
                .count();
        if (duplicateCount != WINNING_SIZE) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호에 중복 숫자가 존재합니다.");
        }
    }
    public static void validateNonNumber(String input) {
        String regExp = "^[0-9]+$";
        if (!input.matches(regExp)){
            throw new IllegalArgumentException("[ERROR] 당첨 번호에 숫자 이외의 값이 들어오면 안됩니다.");
        }
    }

}
