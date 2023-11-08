package lotto.config;

public enum LottoConfig {
    START_LOTTO_NUM(1),
    END_LOTTO_NUM(45),
    LOTTO_COUPON_PRIZE(1000),
    LOTTO_SLOT_NUMBER(6)
    ;
    private int num;
    private LottoConfig(int num) {
        this.num = num;
    }
    public int getNum() {return this.num;}
}
