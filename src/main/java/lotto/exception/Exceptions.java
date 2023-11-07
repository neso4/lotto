package lotto.exception;

import java.util.List;

public class Exceptions {
    public void isInvalidPurchaseMoneyAmount(int money, int divided) {
        if (money % divided != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 1000원단위로 가능합니다.");
        }
    }

    public void isInvalidNumberOfLotteries(String input) {
        String[] lotteries = input.split(",");
        if (lotteries.length != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개를 입력해주셔야 합니다.");
        }
    }

    public void isInvalidNumberOfBonusNumber(int bonus) { // try catch vs if ?
        try {
            if (bonus <= 1 || bonus >= 45) {
                throw new IllegalArgumentException("[ERROR] 보너스 번호의 값은 1~45까지 입력 가능합니다.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 숫자 형식입니다.");
        }
    }

    public void isInvalidNullNumber(String number) {
        if (number.contains(" ") || number.contains(",,")) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백이거나 null값이 될 수 없습니다.");
        }
    }
}
