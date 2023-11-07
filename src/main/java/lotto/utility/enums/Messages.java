package lotto.utility.enums;

public enum Messages {
    INPUT_LOTTO_PURCHASE_AMOUNT_MESSAGE("구입금액을 입력해 주세요."),
    LOTTO_COUNT_MESSAGE("개를 구매했습니다.");

    private String message;

    Messages(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
