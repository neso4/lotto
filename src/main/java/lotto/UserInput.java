package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class UserInput {
    private static final TypeChanger typeChanger = TypeChanger.getTypeChanger();

    public int money() {
        String input = Console.readLine();
        int money = typeChanger.from(input);
        isValidMoney(money);
        return money;
    }

    private void isValidMoney(int money) {
        ValidateMoney.isOverThousand(money);
        ValidateMoney.isUnitFollowed(money);
        ValidateMoney.isMoneyInRange(money);
    }

    public List<Integer> lottoNumber() {
        String input = Console.readLine();
        List<Integer> lottoNumbers = from(input);
        isValidNumber(lottoNumbers);
        return lottoNumbers;
    }

    private List<Integer> from(String input) {
        List<String> numbers = typeChanger.stringToListWithComma(input);
        List<Integer> lottoNumbers = typeChanger.from(numbers);
        return lottoNumbers;
    }

    private void isValidNumber(List<Integer> lottoNumbers) {
        ValidateUserNumber.isLengthSix(lottoNumbers);
        ValidateUserNumber.isDuplicate(lottoNumbers);
        ValidateUserNumber.isInRange(lottoNumbers);
    }
}
