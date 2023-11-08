package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.regex.Pattern;

public class InputBonusNumber {

    private static final Pattern PATTERN = Pattern.compile("\\d+");

    public Integer getInput(){
        System.out.println("\n보너스 번호를 입력해 주세요.");
        String bonusNumber = Console.readLine();
        checkValidate(bonusNumber);
        checkValidateRange(bonusNumber);
        return Integer.parseInt(bonusNumber);
    }

    private void checkValidate(String bonusNumber) {
        if (!PATTERN.matcher(bonusNumber).matches()) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 입력값 입니다.");
        }


    }

    public void checkValidateRange(String bonusNumber) {
        if ( Integer.parseInt(bonusNumber) < 1 || Integer.parseInt(bonusNumber) > 45 ) {
            throw new IllegalArgumentException("[ERROR] 지정된 범위에 벗어나는 숫자입니다.");
        }
    }
}
