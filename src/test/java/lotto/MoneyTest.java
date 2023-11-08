package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.model.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @DisplayName("입력한 금액이 1000원 단위가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"500", "1250", "15200", "-1", "0"})
    void createMoneyByInvalidFormat(String money) {
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력한 금액이 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "가", "."})
    void createMoneyByString(String money) {
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력한 금액이 공백이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void createMoneyByBlank(String money) {
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
