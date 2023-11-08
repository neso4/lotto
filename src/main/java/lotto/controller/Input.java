package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.Lotto;
import lotto.view.View;

public class Input {

    public static int moneyForBuyLotto() {
        while (true) {
            try {
                View.moneyForBuyLotto();
                var input = Console.readLine();
                View.println();

                return Tool.unsafeString2Int(input);
            } catch (IllegalArgumentException e) {
                View.error("숫자만 입력 가능");
            }
        }
    }


    public static Lotto prizeNumber() {
        while (true) {
            try {
                final String SEPARATION_PATTERN = ",";
                View.inputPrizeNumber();
                String input = Console.readLine();
                List<String> numString = Arrays.stream(input.split(SEPARATION_PATTERN)).toList();
                List<Integer> numbers = new ArrayList<>();

                for (String s : numString) {
                    int value = Tool.unsafeString2Int(s);
                    Tool.uniqueAdd(numbers, value);
                }
                Validation.validatePrizeNumbers(numbers);
                View.println();
                return new Lotto(numbers);

            } catch (IllegalArgumentException e) {
                View.error(e.getMessage());
            }
        }
    }

    public static int bonusNumber(Lotto prizeNumber) {
        while (true) {
            try {
                View.inputBonusNumber();
                String str = Console.readLine();
                int num = Tool.unsafeString2Int(str);
                Validation.validateNumberRange(num);
                Validation.isBonusNumberExceptPrizeNumber(prizeNumber, num);
                View.println();
                return num;
            } catch (IllegalArgumentException e) {
                View.error(e.toString());
            }
        }

    }

}
