package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.Objects;

public class LottoNumber {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private int number;

    public LottoNumber() {
        this.number = createRandomNumber();
    }

    public LottoNumber(int number) {
        this.number = number;
    }

    private int createRandomNumber() {
        return Randoms.pickNumberInRange(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
