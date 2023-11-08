package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.HashSet;
import java.util.Set;

public class InputView {

    public static int inputPurchaseAmount() {
        while (true) {
            try {
                System.out.println("구입 금액을 입력해주세요.");
                int amount = Integer.parseInt(Console.readLine());
                if (amount <= 0 || amount % 1000 != 0) {
                    throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위의 양수여야 합니다.");
                }
                return amount;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자를 입력해주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String inputWinningNumbers() {
        while (true) {
            try {
                System.out.println("당첨 번호를 입력해주세요.");
                String numbers = Console.readLine();
                validateWinningNumbers(numbers);
                return numbers;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void validateWinningNumbers(String numbers) {
        String[] splitNumbers = numbers.split(",");
        if (splitNumbers.length != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (String number : splitNumbers) {
            int num = Integer.parseInt(number.trim());
            if (num < 1 || num > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
            if (!uniqueNumbers.add(num)) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
            }
        }
    }


    public static int inputBonusNumber(String winningNumbers) {
        Set<Integer> uniqueNumbers = convertToSet(winningNumbers);
        while (true) {
            try {
                System.out.println("보너스 번호를 입력해주세요.");
                int bonus = Integer.parseInt(Console.readLine());
                validateBonusNumber(bonus, uniqueNumbers);
                return bonus;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Set<Integer> convertToSet(String numbers) {
        String[] splitNumbers = numbers.split(",");
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (String number : splitNumbers) {
            int num = Integer.parseInt(number.trim());
            uniqueNumbers.add(num);
        }
        return uniqueNumbers;
    }

    private static void validateBonusNumber(int bonus, Set<Integer> winningNumbers) {
        if (bonus < 1 || bonus > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
        if (winningNumbers.contains(bonus)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
