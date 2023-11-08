package lotto.Input;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class InputView {
    public static int readPurchaseAmount() {
        while (true) {
            try {
                int purchaseAmount = Integer.parseInt(Console.readLine());
                return purchaseAmount;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 구매 금액은 정수만 입력해 주세요.",e);
            }
        }
    }

    public static List<Integer> readAnswerNumber(){
        String answers = Console.readLine();
        List<Integer> answerNumbers = Arrays.stream(answers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return answerNumbers;
    }

    public static int readBonusNumber(){
        int purchaseAmount = Integer.parseInt(Console.readLine());
        return purchaseAmount;
    }
}
