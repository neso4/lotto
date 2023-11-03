package lotto.validation;

import lotto.view.OutputView;

public class Validator {
    private static Integer PRICE=1000;

    public static void moneyValidate(String input) {
        Integer money = isNum(input);
        noMoneyValidate(money);
        divideValidate(money);
    }

    public static void winningValidate(String[] input) {
        blankValidate(input);
        winningNumValidate(input);
    }

    public static void bonusValidate(String input) {
        isNum(input);
    }

    private static Integer isNum(String input){
        Integer money;

        try {
            money = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            OutputView.errorMessage(ErrorMessage.NUM_ERROR.getMessage());

            throw new IllegalArgumentException();
        }

        return money;
    }

    private static void noMoneyValidate(Integer money) {
        if(money<PRICE){
            OutputView.errorMessage(ErrorMessage.NO_MONEY.getMessage());

            throw new IllegalArgumentException();
        }
    }

    private static void divideValidate(Integer money) {
        if(money % PRICE !=0){
            OutputView.errorMessage(ErrorMessage.DIVIDE_ERROR.getMessage());

            throw new IllegalArgumentException();
        }
    }

    private static void blankValidate(String[] winningNum) {
        for (String num : winningNum) {
            if(num.trim().isEmpty()){
                OutputView.errorMessage(ErrorMessage.BLANK_ERROR.getMessage());

                throw new IllegalArgumentException();
            }
        }
    }

    private static void winningNumValidate(String[] winningNum) {
        for (String num : winningNum) {
            isNum(num);
        }
    }
}
