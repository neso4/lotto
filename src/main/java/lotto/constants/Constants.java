package lotto.constants;

public class Constants {
    public enum Integers {
        LOTTO_PRICE(1000), NOTHING_REMAIN(0),
        MIN_LOTTO_NUMBER(1), MAX_LOTTO_NUMBER(45), LOTTO_SIZE(6),
        ONE_HUNDRED(100),
        INTEGER_ZERO(0), RANK_COUNT_INCREASE(1),
        ITERATE_START_NUMBER(1),
        ;

        private int value;

        Integers(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum Strings {
        LOTTO_NUMBERS_INPUT_DELIMITER(",");

        private String value;

        Strings(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
