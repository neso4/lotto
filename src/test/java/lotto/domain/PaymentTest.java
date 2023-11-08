package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PaymentTest {

    @DisplayName("Payment 객체 생성을 실패한다.")
    @Test
    void createSuccessTest() {
        assertThatThrownBy(() -> new Payment(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("지불 금액 대비 수익률 퍼센트를 반환한다.")
    @Test
    void calculateProfitPercentageSuccessTest() {
        // given
        Payment payment = new Payment(5000);

        // when
        double profitPercentage = payment.calculatePercentageOfProfit(10000);

        // then
        assertThat(profitPercentage).isEqualTo(200.0);
    }

    @DisplayName("지불 금액으로 구매 가능한 개수를 반환한다.")
    @Test
    void countAffortableSuccessTest() {
        // given
        Payment payment = new Payment(5000);

        // when
        int countAffortable = payment.countAffortable(1000);

        // then
        assertThat(countAffortable).isEqualTo(5);
    }
}
