package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CustomerTest {
    @DisplayName("글자를 입력하거나 터무니 없는 값을 입력하면 예외가 발생한다.")
    @Test
    void createCustomerByNotNumber() {
        assertThatThrownBy(() -> new Customer("테스트"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 1000원부터 100,000까지의 금액을 입력해주세요.");
    }

    @DisplayName("로또 구매 금액이 100,000원을 넘어선 안 된다.")
    @Test
    void createCustomerByOverMoney() {
        assertThatThrownBy(() -> new Customer("106000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 구매 금액은 최소 1000원부터 최대 10만원까지입니다.");
    }
}