package lotto.View;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

import static lotto.Domain.Lotto.*;

public class InputView {
    private static final int LOTTO_PRICE = 1000;

    public static int getPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int amount = getMoney();
        try {
            if (amount % LOTTO_PRICE != 0) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 1,000원 단위로 입력해 주세요.");
            amount = getMoney();
        }

        return amount / LOTTO_PRICE;
    }

    public static int getMoney() {
        String input = Console.readLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자로 된 금액을 입력해 주세요.");
            return getMoney();
        }
    }


    public static List<Integer> getWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String[] numbertmp = Console.readLine().split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String number : numbertmp) {
            numbers.add(Integer.parseInt(number));
        }
        validateDuplicate(numbers);
        validateRange(numbers);
        return numbers;
    }


    public static int getBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        int bonus = Integer.parseInt(Console.readLine());
        if (bonus < 1 || bonus > 45) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
        return bonus;
    }
}
