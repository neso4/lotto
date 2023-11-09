package lotto.domain;

import java.util.List;

public class LottoNumber {
    private static final String NEW_LINE = "\n";
    private final List<Integer> lottoNumber;

    public LottoNumber(List<Integer> lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public boolean calculateScore(int number) {
        return lottoNumber.contains(number);
    }

    @Override
    public String toString() {
        return lottoNumber.toString() + NEW_LINE;
    }
}
