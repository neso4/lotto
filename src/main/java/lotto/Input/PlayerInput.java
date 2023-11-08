package lotto.Input;

import camp.nextstep.edu.missionutils.Console;
import lotto.Validate.ErrorMessage;
import lotto.Validate.ErrorValidate;

import java.util.ArrayList;
import java.util.List;

public class PlayerInput {
    private static final int Lotto_PRICE = 1000;

    //금액 입력받기
    public String inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();
        try {
            ErrorValidate.isNumberFormat(input);
            ErrorValidate.isMinimumInputMoney(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            System.out.println(ErrorMessage.ERROR_IS_NUMBER.getErrorMessage());
            input = inputMoney();
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorMessage.ERROR_MINIMUM_INPUT_MONEY.getErrorMessage());
            input = inputMoney();
        }
        return input;
    }

    public List<Integer> inputLottoNumber() {
        List<Integer> winNumber = new ArrayList<>();
        System.out.println("\n당첨 번호를 입력해 주세요.");
        String input = Console.readLine();
        for (String number : input.split(",")) {
            try {
                ErrorValidate.inputNumberLimit(Integer.parseInt(number));
            } catch (IllegalArgumentException e) {
                System.out.println(ErrorMessage.ERROR_NUMBER_RANGE.getErrorMessage());
                return inputLottoNumber();
            }
            winNumber.add(Integer.parseInt(number));
        }
        System.out.println();
        return winNumber;
    }

    public int inputBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        int bonusNumber = Integer.parseInt(Console.readLine());
        System.out.println();
        return bonusNumber;
    }
}
