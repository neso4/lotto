package lotto.view;

import static lotto.util.util.println;

import camp.nextstep.edu.missionutils.Console;

/* 입력 '만' 받는 클래스
*  중복 코드 인자 이용해 줄일 수 있을 듯 : 입력 메세지 - 입력 받기
* */
public class InputView {
    String INPUT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    String INPUT_WINNING_NUMBER = "당첨 번호를 입력해 주세요.";
    String INPUT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";


    // 구입금액 입력 메세지 출력
    public void printInputPurchaseAmountMessage() {
        println(INPUT_PURCHASE_AMOUNT);
    }

    // 구입금액 입력 받기
    public String inputPurchaseAmount() {
        return Console.readLine();
    }

    // 당첨번호 입력 메세지 출력
    public void printInputWinningNumberMessage() {
        println(INPUT_WINNING_NUMBER);
    }

    // 당첨번호 입력 받기
    public String inputWinningNumbers() {
        return Console.readLine();
    }

    // 보너스번호 입력 메세지 출력
    public void printInputBonusNumberMessage() {
        println(INPUT_BONUS_NUMBER);
    }

    // 보너스번호 입력 받기
    public String inputBounsNumber() {
        return Console.readLine();
    }
}
