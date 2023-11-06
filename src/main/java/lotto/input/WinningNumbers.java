package lotto.input;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashSet;
import java.util.Set;

public class WinningNumbers {
    public static Set<Integer> getWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        while (true) {
            try {
                return inputNumbers();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Set<Integer> inputNumbers() {
        Set<Integer> winningNumbers = new HashSet<>();
        String input = Console.readLine();
        String[] inputNumbers = input.split(",");
        validateInput(inputNumbers, winningNumbers);
        return winningNumbers;
    }

    protected static void validateInput(String[] inputNumbers, Set<Integer> winningNumbers) {
        if (inputNumbers.length != 6) {
            throw new IllegalArgumentException("[ERROR] 숫자 6개를 입력해주세요.");
        }
        for (String number : inputNumbers) {
            int winningNum = validateNumberFormat(number.trim());
            validateNumberRange(winningNum);
            validateDuplicate(winningNum, winningNumbers);
        }
    }


    private static int validateNumberFormat(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 입력이 있습니다.");
        }
    }

    private static void validateNumberRange(int numberInput) {
        if (numberInput < 1 || numberInput > 45) {
            throw new IllegalArgumentException("[ERROR] 1과 45 사이의 숫자 6개를 입력해주세요.");
        }
    }

    private static void validateDuplicate(int numberInput, Set<Integer> winningNumbers) {
        if (!winningNumbers.add(numberInput)) {
            throw new IllegalArgumentException("[ERROR] 중복되지 않는 숫자 6개를 입력해주세요.");
        }
    }

}