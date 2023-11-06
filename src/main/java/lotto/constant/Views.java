package lotto.constant;

public enum Views {
    INPUT_PURCHASE_AMOUT_MESSAGE("구입금액을 입력해 주세요 ."),
    OUTPUT_PURCHASE_AMOUT_MESSAGE("개를 구매했습니다 ."),
    INPUT_WINNING_NUMBER_MESSAGE("당첨 번호를 입력해 주세요."),
    INPUT_BONUS_NUMBER_MESSAGE("보너스 번호를 입력해 주세요.");


    private final String message;

    Views(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
