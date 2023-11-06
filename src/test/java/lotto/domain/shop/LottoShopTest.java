package lotto.domain.shop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoShopTest {

    private LottoShop lottoShop = new LottoShop();

    @DisplayName("돈을 지불했을 때, 알맞는 개수의 로또를 판매한다.")
    @ParameterizedTest
    @CsvSource(value = {"1000,1", "2000,2", "3000,3"})
    void purchaseTest(int cash, int expectedCount) {
        // when
        int purchasableCount = lottoShop.countPurchasableAmount(cash);

        // then
        Assertions.assertThat(purchasableCount)
                .isEqualTo(expectedCount);
    }

}
