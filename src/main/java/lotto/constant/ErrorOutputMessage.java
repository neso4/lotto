package lotto.constant;

public final class ErrorOutputMessage {
    
    private static final String ERROR = "[ERROR] ";

    public static final String PURCHASE_TYPE 
            = ERROR + "구매 금액은 숫자로만 이루어져야 합니다.";

    public static final String PURCHASE_REMAINDER 
            = ERROR + "구매 금액은 " + LottoNumber.PURCHASE__PRICE.getNumber() + "으로 나누어 떨어져야 합니다.";

    public static final String NUMBER_COUNT
            = ERROR + "당첨 번호는 " + LottoNumber.LOTTO__SIZE.getNumber() + "개를 입력해야 합니다.";

    public static final String NUMBER_BLANK
            = ERROR + "당첨 번호는 ,(쉼표)로 구분하여 입력해야 되며, 공백을 포함할 수 없습니다.";

    public static final String NUMBER_DUPLICATE
            = ERROR + "당첨 번호는 중복될 수 없습니다.";

    public static final String NUMBER_RANGE
            = ERROR + "로또 번호는 " + LottoNumber.FIRST__RANGE.getNumber() + "부터 " + LottoNumber.LAST__RANGE.getNumber() + " 사이의 숫자여야 합니다.";

    public static final String BONUS_NUMBER_DUPLICATE
            = ERROR + "보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    public static final String BONUS_NUMBER_BLANK
            = ERROR + "보너스 번호는 당첨 번호와 중복될 수 없습니다.";         
}
