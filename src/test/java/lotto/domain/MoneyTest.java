package lotto.domain;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class MoneyTest {

    Money money;

    @BeforeEach
    void setUp() {
        money = new Money("1000");
    }

    @Test
    @DisplayName("money를 반환한다.")
    void getMoneyTest() {
        //given

        //when
        int temp = money.getMoney();

        //then
        assertThat(temp).isEqualTo(1000);
    }

    @Test
    @DisplayName("money 나누기 1000원 한 값을 quantity로 반환한다.")
    void getQuantityTest() {
        //given

        //when
        int quantity = money.getQuantity();

        //then
        assertThat(quantity).isEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("숫자가 아닌 입력일시 예외를 발생한다.")
    @ValueSource(strings = {"-1", "a", "abc", " 1", "1 "})
    void validateOnlyNumberTest(String userInput) {
        //given

        //when

        //then
        assertThatThrownBy(() -> new Money(userInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("1000원 미만을 입력하면 예외를 발생한다.")
    @ValueSource(strings = {"100", "250", "999"})
    void validateAboveOrEqualThousandTest(String userInput) {
        //given

        //when

        //then
        assertThatThrownBy(() -> new Money(userInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("1000원 단위로 나누어 떨어지지 않는 금액을 입력시 예외를 발생한다.")
    @ValueSource(strings = {"1240", "5500", "99999"})
    void validateDivisibleByThousandTest(String userInput) {
        //given

        //when

        //then
        assertThatThrownBy(() -> new Money(userInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
