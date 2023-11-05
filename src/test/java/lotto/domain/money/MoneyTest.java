package lotto.domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MoneyTest {

    @DisplayName("정수를 통해 생성할 수 있다.")
    @Test
    void createByInteger() {
        // given
        final int value = 1000;

        // when
        Money money = Money.valueOf(value);

        // then
        assertThat(money).isInstanceOf(Money.class);
        assertThat(money.getValue()).isEqualTo(value);
    }

    @DisplayName("입력한 금액 만큼 대금을 지불할 수 있다.")
    @Test
    void payInputPrice() {
        // given
        final Money money = Money.valueOf(3000);
        final Money price = Money.valueOf(1000);

        // when
        money.pay(price);

        // then
        assertThat(money.getValue()).isEqualTo(2000);
    }

    @DisplayName("잔액으로 물건을 구매할 수 없는 경우 예외를 발생한다.")
    @Test
    void cannotPay_whenBalanceIsInsufficientForPay() {
        // given
        final Money money = Money.valueOf(500);
        final Money price = Money.valueOf(1000);

        // when, then
        assertThatThrownBy(() -> money.pay(price))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("잔액 500원 으로는 1000원의 물건을 구매하실 수 없습니다.");
    }

    @DisplayName("구매 가능 여부를 알려준다.")
    @ParameterizedTest(name = "[{index}] : money = {0}, price = {1}, expected = {2}")
    @CsvSource(value = {"1000, 500, true", "500, 1000, false"})
    void indicatesPurchaseAvailability(final int moneyValue,
                                       final int priceValue,
                                       final boolean expected) {
        // given
        final Money money = Money.valueOf(moneyValue);
        final Money price = Money.valueOf(priceValue);

        // when
        boolean result = money.canPay(price);

        // then
        assertThat(result).isEqualTo(expected);
    }
}