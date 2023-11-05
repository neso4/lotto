package lotto.domain;

import lotto.util.LottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomerTest {
    @DisplayName("입력한 금액만큼 로또를 구매한다.")
    @Test
    void purchaseLottos() {
        // given
        Cash cash = new Cash(3000);
        LottoSeller lottoSeller = new LottoSeller(new LottoNumberGenerator());
        Customer customer = new Customer(cash, lottoSeller);

        // when
        customer.purchaseLottos();

        // than
        assertThat(customer.getLottos()).isEqualTo(3);
    }
}