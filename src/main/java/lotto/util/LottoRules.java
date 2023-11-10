package lotto.util;

public enum LottoRules {
    PRICE_PER_LOTTO(1000),
    START_NUMBER(1),
    END_NUMBER(45),
    LOTTO_NUMBER_COUNT(6),
    PRINT_RESULT_COUNT(5),
    FIRST_PRIZE(6),
    SECOND_PRIZE(5),
    THIRD_PRIZE(4),
    FORTH_PRIZE(3),
    ;



    private final int value;
    LottoRules(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}
