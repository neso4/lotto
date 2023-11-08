package lotto.constant;

public class MyConstant {
    public static String MSG_SIZE_LOTTO_ENVELOP(Integer size) {
        return size + "개를 구매했습니다.";
    }

    public static String MSG_RATE_RESULT(Double rate) {
        return "총 수익률은 " + rate + "%입니다.";
    }

    public static String MSG_STATISTIC_FIFTH_PLACE(Integer count) {
        return "3개 일치 (5,000원) - " + count + "개\n";
    }
}
