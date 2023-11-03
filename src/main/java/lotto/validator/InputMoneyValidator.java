package lotto.validator;

public class InputMoneyValidator {

    private static final String ERROR = "[ERROR]";
    private static final String NULL_ERROR_MESSAGE = "금액을 입력해주세요.";
    private static final String NOT_NUMBER_ERROR_MESSAGE = "숫자만 입력해주세요.";
    private final String money;

    public InputMoneyValidator(String input){
        money = input;
        isNull();
        isNumError();
    }

    public void isNull(){
        if (money.equals("")){
            throw new IllegalArgumentException(ERROR + NULL_ERROR_MESSAGE);
        }
    }

    public void isNumError(){
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR + NOT_NUMBER_ERROR_MESSAGE);
        }
    }

}
