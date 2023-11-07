package lotto.domain;

public class LottoNumber implements Comparable<LottoNumber> {

    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;
    private final int number;

    public LottoNumber(int number) {
        validateNumberRange(number);
        this.number = number;
    }

    private void validateNumberRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException();
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return o.number - number;
    }
}
