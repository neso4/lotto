package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BuyLottoTest {

    @Test
    void 입력한_금액만큼_로또_구매() {
        Money money = new Money(10000);
        BuyLotto buyLotto = new BuyLotto();
        assertThat(buyLotto.lotto(money)).isInstanceOf(LottoNumbers.class);
    }
}
