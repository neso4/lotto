package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {

    public static int inputPurchaseAmount(){
        String purchaseAmount = Console.readLine();
        validateNotEmpty(purchaseAmount);
        validateIntegerType(purchaseAmount);
        return Integer.parseInt(purchaseAmount);
    }

    private static void validateIntegerType(String purchaseAmount) {
        Pattern pattern = Pattern.compile("^-?\\d+$");
        Matcher matcher = pattern.matcher(purchaseAmount);

        if(!matcher.matches()){
            throw new IllegalArgumentException("[ERROR] : 입력 값은 정수 타입이어야 합니다");
        }
    }

    private static void validateNotEmpty(String purchaseAmount) {
        if(purchaseAmount.isEmpty()){
            throw new IllegalArgumentException("[ERROR] : 입력 값은 공백일 수 없습니다");
        }
    }

}
