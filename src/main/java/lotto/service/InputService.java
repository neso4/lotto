package lotto.service;

import camp.nextstep.edu.missionutils.Console;
import lotto.type.ErrorMessageType;

import java.util.Arrays;

public class InputService {
    public static String inputMoney() {
        System.out.println("구입 금액을 입력해 주세요");
        String money;
        money = Console.readLine();
        return money;
    }

    public static String inputNumbers() {
        System.out.println("당첨 번호를 입력해주세요.");
        String numbers = Console.readLine();
        return numbers;
    }

    public static String inputBonusNumber() {
        System.out.println("보너스 번호를 입력해주세요.");
        String number = Console.readLine();
        return number;
    }

    public static ErrorMessageType compareErrorMessageType(String message) {
        return Arrays.stream(ErrorMessageType.values())
                .filter(errorType -> errorType.message().equals(message))
                .findFirst()
                .orElse(null);
    }
}
