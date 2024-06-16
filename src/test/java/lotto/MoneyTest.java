package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @DisplayName("구입 금액 입력받기")
    @Test
    void nonNumericTest() throws MyException {
        Money money = new Money();
        assertThatThrownBy(money.getModulo(50))
        .isInstanceOf(MyException.class);
    }

    @DisplayName("랜덤숫자 보여주기")
    @Test
    void showRandomNumber() throws MyException {
        Money money = new Money();
        assertThatThrownBy(money.showNum(8))
                .isInstanceOf(MyException.class);
    }
}
