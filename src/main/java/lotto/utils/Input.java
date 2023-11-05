package lotto.utils;

import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Input {
    public static int readPurchaseAmount() {
        String userInput = readLine();
        return Validate.purchaseAmount(userInput);
    }

    public static Lotto readLottery() {
        String userInput = readLine();
        String regex = ",";
        String[] numberStrings = userInput.split(regex);
        List<Integer> numbers = new ArrayList<>();

        for (String numberString : numberStrings) {
            int number = Validate.lotteryNumber(numberString);
            numbers.add(number);
        }

        return new Lotto(numbers);
    }

    public static int readBonusNumber() {
        String userInput = readLine();
        return Validate.lotteryNumber(userInput);
    }

}
