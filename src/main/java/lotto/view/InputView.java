package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.message.GameMessage;
import lotto.message.ValidateErrorMessage;

public class InputView {
    public int getPayment() {
        try {
            System.out.println(GameMessage.PAYMENT_INPUT_MESSAGE);
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ValidateErrorMessage.PAYMENT_ERROR.getMessage());
        }
    }
    public List<Integer> getNumbers(){
        try {
            System.out.println(GameMessage.NUMBERS_INPUT_MESSAGE);
            return Arrays
                    .stream(Console.readLine().split(","))
                    .map(Integer::parseInt).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}