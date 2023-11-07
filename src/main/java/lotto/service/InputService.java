package lotto.service;

import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static lotto.domain.constant.StringConstant.*;
import static lotto.utils.Validator.*;

public class InputService {
    public static long readExpense() {
        try {
            String input = readLine();

            validateNumberType(input);
            validateExpenseValue(input);

            return parseLong(input);
        } catch (IllegalArgumentException e) {
            return readExpense();
        }
    }


    public static List<Integer> readWinningNumbers() {
        List<Integer> winningNumbers = new ArrayList<>();
        String[] input = trim(readLine().split(SPLIT_DELIMITER));

        for (String num : input) {
            try {
                validateNumberType(num);
                winningNumbers.add(parseInt(num));
            } catch (IllegalArgumentException e) {
                return readWinningNumbers();
            }
        }

        winningNumbers.sort(null);
        return winningNumbers;
    }

    private static String[] trim(String[] input) {
        String[] trimmed = new String[input.length];

        for (int i = 0; i < input.length; i++)
            trimmed[i] = input[i].trim();

        return trimmed;
    }


    public static int readBonusNumber(Lotto winningLotto) {
        try {
            String input = readLine();
            validateNumberType(input);

            int bonusNumber = parseInt(input);
            validateBonusNumInRange(bonusNumber);
            validateBonusNumDuplicate(bonusNumber, winningLotto);

            return bonusNumber;
        } catch (IllegalArgumentException e) {
            return readBonusNumber(winningLotto);
        }
    }
}
