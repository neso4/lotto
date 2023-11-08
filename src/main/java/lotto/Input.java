package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;

import static lotto.CheckNumber.*;

public class Input {
    public static int getValidPurchaseInput() { // 구매 금액의 입력이 올바른지 확인하고 올바르지 않으면 재입력한다
        System.out.println("구입금액을 입력해 주세요");
        try {
            String purchaseInput = Console.readLine(); // 구매 금액을 입력한다
            System.out.println();
            return validateInput(purchaseInput); // 입력값이 올바르면 그 값을 리턴하고 종료, 올바르지 않으면 예외 발생
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
        System.out.println();
        return validateRepeatInput(); // 올바르게 입력할 때까지 재입력
    }

    public static int validateInput(String input) { // 올바른 경우 입력 값을 형변환해서 리턴하고, 올바르지 않으면 예외 발생한다
        checkTextInput(input);
        checkPurchaseMoney(Integer.parseInt(input));
        return Integer.parseInt(input);
    }

    public static int validateRepeatInput() { // 올바르게 입력하기 전까지 구매금액을 재입력 받는다
        String purchaseInput = "";
        boolean inputSatisfaction = false;
        while (!inputSatisfaction) { // 입력값이 올바르지 않으면 반복문 진행
            try {
                purchaseInput = Console.readLine(); // 입력값을 받음
                checkTextInput(purchaseInput); // 문자가 포함되어 있는지 확인
                checkPurchaseMoney(Integer.parseInt(purchaseInput)); // 1000원 이상이며 1000으로 나누어 지는지 확인
                inputSatisfaction = true;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
        return Integer.parseInt(purchaseInput); // 올바르게 입력한 경우 입력값을 형변환하여 리턴
    }
    public static String getValidPrizeNumber() { // 당첨 번호의 입력이 올바른지 확인하고 올바르지 않으면 재입력한다
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
        try {
            String prizeInput = Console.readLine(); // 당첨 번호를 입력한다
            return validatePrizeInput(prizeInput); // 입력값이 올바르면 그 값을 리턴하고 종료, 올바르지 않으면 예외 발생
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

        return validateUserPrizeInput(); // 올바르게 입력할 때까지 재입력
    }

    public static String validatePrizeInput(String prizeInput) { // 올바른 경우 입력 값을 리턴하고, 올바르지 않으면 예외 발생한다
        checkPrizeNumber1(prizeInput);
        checkPrizeNumber2(prizeInput);
        return prizeInput;
    }

    public static String validateUserPrizeInput() { // 올바르게 입력하기 전까지 당첨 번호를 재입력 받는다
        boolean satisfaction = false;
        String prizeNumber = "";
        while (!satisfaction) { // 입력값이 올바르지 않으면 반복문 진행
            try {
                prizeNumber = Console.readLine(); // 입력값을 받음
                checkPrizeNumber1(prizeNumber); // 숫자가 6개가 아니거나, 숫자로만 구성되어 있지 않거나, 0이하거나 45보다 큰 수가 있는지 확인
                checkPrizeNumber2(prizeNumber); // 동일한 숫자가 존재하는지 확인
                satisfaction = true;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
        return prizeNumber;
    }
    public static String getValidBonusNumber(ArrayList<Integer> prizeNumberArray) { // 보너스 번호의 입력이 올바른지 확인하고 올바르지 않으면 재입력한다
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");
        try {
            String bonusInput = Console.readLine(); // 보너스 번호를 입력한다
            return validateBonusInput(bonusInput, prizeNumberArray); // 입력값이 올바르면 그 값을 리턴하고 종료, 올바르지 않으면 예외 발생
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

        return validateUserBonusInput(prizeNumberArray); // 올바르게 입력할 때까지 재입력
    }

    public static String validateBonusInput(String bonusInput, ArrayList<Integer> prizeNumberArray) { // 올바른 경우 입력 값을 리턴하고, 올바르지 않으면 예외 발생한다
        checkTextInput(bonusInput);
        checkBonusNumber(bonusInput, prizeNumberArray);
        return bonusInput;
    }

    public static String validateUserBonusInput(ArrayList<Integer> prizeNumberArray) { // 올바르게 입력하기 전까지 보너스 번호를 재입력 받는다
        String bonusNumber = "";
        boolean bonusSatisfaction = false;
        while (!bonusSatisfaction) { // 입력값이 올바르지 않으면 반복문 진행
            try {
                bonusNumber = Console.readLine(); // 입력값을 받음
                checkTextInput(bonusNumber); // 문자가 포함되어 있는지 확인
                checkBonusNumber(bonusNumber, prizeNumberArray); // 값이 없거나, 0이하거나 45보다 큰 수거나, 당첨 번호에 존재하는 값인지 확인
                bonusSatisfaction = true;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
        return bonusNumber;
    }
}