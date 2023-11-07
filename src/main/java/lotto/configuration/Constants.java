package lotto.configuration;

public class Constants {

    public static final class Error {
        public static final String MESSAGE = "[ERROR] ";
        public static final String NEED_MULTIPLE_OF_PRICE = "의 배수의 숫자를 입력해야 합니다.";
        public static final String CONVERT_NUMBER_ERROR = "숫자 변환에 실패했습니다.";
        public static final String LOTTO_IS = "로또 번호는 ";
        public static final String HAS_DIGIT = "자리 입니다.";
        public static final String MUST_NOT_DUPLICATE = "로또 번호는 중복되지 않아야 합니다.";
        public static final String FROM = "부터 ";
        public static final String MUST_CHECK_RANGE = " 사이의 숫자여야 합니다.";
        public static final String BONUS_MUST_NOT_DUPLICATE = "로또 번호와 보너스 번호는 중복되지 않아야 합니다.";
        public static final String BUSINESS_LOGIC = "내부 비즈니스 로직 에러";
    }

    public static final class Rule {
        public static final int PRICE = 1000;
        public static final String DELIM = ",";
    }


    public static final class Message {
        public static final String NEED_PURCHASE_MONEY = "구입금액을 입력해 주세요.";
        public static final String BUY_LOTTO = "개를 구매했습니다.\n";
        public static final String ENTER_WINNING_LOTTO = "당첨 번호를 입력해 주세요.";
        public static final String NEED_BONUS_NUMBER = "\n보너스 번호를 입력해 주세요.";
    }

}
