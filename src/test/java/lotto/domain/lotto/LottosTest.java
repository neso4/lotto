package lotto.domain.lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class LottosTest {
    private Lottos lottos;

    @BeforeEach
    void setUp() {
        final List<Lotto> lottoList = List.of(
                new Lotto(LottoNumbers.from(List.of(1, 2, 3, 4, 5, 6))),
                new Lotto(LottoNumbers.from(List.of(7, 1, 2, 3, 4, 5))),
                new Lotto(LottoNumbers.from(List.of(1, 2, 3, 4, 5, 18))),
                new Lotto(LottoNumbers.from(List.of(1, 2, 3, 4, 23, 26))),
                new Lotto(LottoNumbers.from(List.of(1, 2, 3, 28, 29, 30)))
        );
        lottos = new Lottos(lottoList);
    }

    @Test
    void 구매_금액_계산() {
        // when
        final int purchasingCost = lottos.getPurchasingCost();

        // then
        assertThat(purchasingCost).isEqualTo(5000);
    }
}
