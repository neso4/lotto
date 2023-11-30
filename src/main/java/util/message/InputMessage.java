package util.message;

import util.EnumUtil;

public enum InputMessage implements EnumUtil<String, String> {
    GET_PURCHSE_AMOUNT("구입금액을 입력해 주세요."),
    GET_WINNING_NUMBERS("\n당첨 번호를 입력해 주세요."),
    GET_BONUS_NUMBER("\n보너스 번호를 입력해 주세요.");
    private final String message;

    InputMessage(final String message){
        this.message = message;
    }


    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return message;
    }
}
