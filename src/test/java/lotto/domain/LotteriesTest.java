package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LotteriesTest {

    RandomNumberGenerator numberGenerator = new RandomNumberGenerator();

    @Test
    void 구매_수량에_따라_로또가_발행되는지_확인() {
        int purchaseCount = 5;
        Lotteries lotteries = Lotteries.of(5, numberGenerator);

        assertThat(lotteries.getLotteries().size()).isEqualTo(purchaseCount);
    }
}