package lotto.domain;

public class LottoNumber {

    public static final String OUT_OF_LOTTO_VALUE_RANGE = "[ERROR] 로또 입력 값은 1이상 45이하의 값만 가능합니다.";
    public static final int LOTTO_RANGE_IN_START_VALUE = 1;
    public static final int LOTTO_RANGE_IN_END_VALUE = 45;

    private final Integer LottoNumber;

    public LottoNumber(Integer lottoNumber) {
        validationInRange(lottoNumber);
        LottoNumber = lottoNumber;
    }

    private boolean range(Integer number) {
        return LOTTO_RANGE_IN_START_VALUE <= number && number <= LOTTO_RANGE_IN_END_VALUE;
    }

    private void validationInRange(Integer number) {
        if (range(number)) {
            throw new IllegalArgumentException(OUT_OF_LOTTO_VALUE_RANGE);
        }
    }
}
