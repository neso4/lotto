package lotto.ui;

import camp.nextstep.edu.missionutils.Console;

public class Computer {

    public static String getInput() {
        System.out.println("구입금액을 입력해 주세요.");
        return Console.readLine();
    }

    public void checkNumber(String userInput) {

        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("[ERROR] 숫자만(소수점 제외) 입력해주세요.");
        }
    }

    public void checkRemainder(Integer userInput) {
        if ((userInput % 1000) != 0) {
            throw new IllegalArgumentException("[ERROR] 1000단위의 숫자를 입력해주세요.");
        }
    }

    public void checkZero(String userInput){
        if(userInput.charAt(0) == '0'){
            throw new IllegalArgumentException("[ERROR] 숫자 앞에 0을 입력하지 마세요.");
        }
    }

}
