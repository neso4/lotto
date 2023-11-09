package lotto;

import static lotto.domain.money.LottoMoneyErrorMessage.MONEY_CANNOT_BE_DIVIDED;
import static lotto.domain.money.LottoMoneyErrorMessage.MONEY_NOT_MORE_THAN_ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.money.Money;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1000, -2000})
    void 로또_구입_금액은_0보다_커야한다(int inputMoney) {
        //when, then
        assertThatThrownBy(() -> new Money(inputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MONEY_NOT_MORE_THAN_ZERO.toString());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 100, 1100, 10001, 110010})
    void 로또_구입_금액은_1000원_단위여야_한다(int inputMoney) {
        //when, then
        assertThatThrownBy(() -> new Money(inputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MONEY_CANNOT_BE_DIVIDED.toString());
    }

    @ParameterizedTest
    @CsvSource(value = {"1000:1", "50000:50", "100000:100", "999000:999"}, delimiter = ':')
    void 구입_금액으로_구매_가능한_복권_수를_계산한다(int inputMoney, int result) {
        //given
        Money money = new Money(inputMoney);

        //when, then
        assertThat(money.getNumberOfLottos()).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"1000:100.0", "5000:500.0", "0:0.0", "55000:5500.0"}, delimiter = ':')
    void 수익률_계산(long prizeMoney, double rateOfReturn) {
        //given
        Money money = new Money(1000);

        //when, then
        assertThat(money.getRateOfReturn(prizeMoney)).isEqualTo(rateOfReturn);
    }


}
