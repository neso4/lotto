package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class MoneyTest {
    @DisplayName("구입 금액이 0이면 예외가 발생한다.")
    @Test
    void createMoneyByZero() {
        assertThatThrownBy(() -> new Money(0))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @DisplayName("구입 금액이 요구사항의 단위에 나눠떨어지지 않으면 예외가 발생한다.")
    @Test
    void createMoneyByValueNotDivisibleByUnit() {
        assertThatThrownBy(() -> new Money(1200))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("성공적으로 머니 인스턴스를 생성한다.")
    @Test
    void createMoneySuccessfully() {
        Assertions.assertAll(() -> new Money(1000));
    }
}
