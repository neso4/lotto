package lotto.domain;

import lotto.util.NumberGenerator;

import java.util.List;

public class LottoMachine {
    private final NumberGenerator numberGenerator;
    private final Cash cash;

    public LottoMachine(NumberGenerator numberGenerator, Cash cash) {
        this.numberGenerator = numberGenerator;
        this.cash = cash;
    }

}
