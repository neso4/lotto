package lotto;


import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

public class User {

    public String getUserInput(String message) {
        System.out.println(message);
        return Console.readLine();
    }

    public Integer getPurchaseAmount() {
        String input = getUserInput("구입금액을 입력해 주세요.");
        return validatePurchaseAmount(input);
    }

    public List<Integer> getWinningNumbers() {
        String input = getUserInput("당첨 번호를 입력해 주세요.");
        return splitWinningNumbers(input);
    }

    public Integer getBonusNumber() {
        String input = getUserInput("보너스 번호를 입력해 주세요.");
        return validateNumber(input);
    }

    private Integer validateNumber(String number) {
        try {
            Integer integerNumber = Integer.parseInt(number);
            return integerNumber;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닙니다.");
        }
    }

    private Integer validatePurchaseAmount(String input) {
        Integer tmp = validateNumber(input);
        if (tmp % 1000 != 0) {
            throw new IllegalArgumentException("로또 구입 금액은 1,000원 단위여야 합니다.");
        }
        return tmp;
    }

    private List<Integer> splitWinningNumbers(String input) {
        String[] numberStrings = input.split(",");
        List<Integer> winningNumbers = new ArrayList<Integer>();
        for (String number : numberStrings) {
            winningNumbers.add(validateNumber(number));
        }
        return winningNumbers;
    }
}
