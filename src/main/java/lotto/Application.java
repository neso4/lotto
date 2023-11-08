package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private static final int LOTTO_PRICE = 1000;

    public static void main(String[] args) {
        int count = askPurchaseAmount() / LOTTO_PRICE;
        System.out.println(count + "개를 구매했습니다.");
        for (int i = 0; i < count; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Lotto lotto = new Lotto(numbers);
            lotto.print();
        }
    }

    static int askPurchaseAmount() {
        System.out.println("구매금액을 입력해 주세요.");
        String input = Console.readLine();
        return validate(input);
    }

    static int validate(String input) {
        int purchaseAmount = 0;
        try {
            purchaseAmount = Integer.parseInt(input);
            if (purchaseAmount % 1000 != 0) {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 구매금액으로 숫자 외의 값을 입력했습니다.");
            return askPurchaseAmount();
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 구매금액이 1,000원으로 나누어 떨어지지 않습니다.");
            return askPurchaseAmount();
        }

        return purchaseAmount;
    }

    static List<Integer> askWinningNumber() {
        List<Integer> numbers = new ArrayList<>();

        System.out.println("당첨 번호를 입력해 주세요.");
        String input = Console.readLine();
        String[] separatedInput = input.split(",");

        for (String item : separatedInput) {
            int number = validateWinningNumber(item);
            numbers.add(number);
        }

        return numbers;
    }

    static int validateWinningNumber(String item) {
        int number = 0;
        try {
            number = Integer.parseInt(item);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 당첨 번호로 숫자 외의 값을 입력했습니다.");
            askWinningNumber();
        }

        return number;
    }

    static int askBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        String input = Console.readLine();
        return validateBonusNumber(input);
    }

    static int validateBonusNumber(String input) {
        int bonusNumber = 0;
        try {
            bonusNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 보너스 번호로 숫자 외의 값을 입력했습니다.");
            askBonusNumber();
        }

        return bonusNumber;
    }
}
