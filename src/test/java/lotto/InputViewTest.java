package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import lotto.exception.InvalidPurchaseAmountException;
import lotto.exception.InvalidSizeException;
import lotto.exception.NonNumericAmountException;
import lotto.fixtures.LottoFixtures;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputViewTest {

    @AfterEach
    void closeConsole() {
        Console.close();
    }

    @DisplayName("사용자가 구입 금액을 1000원 단위로 입력하지 않으면 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"5500", "3700", "1300"})
    void inputWrongMoney(String input) {
        System.setIn(createUserInput(input));

        int money = InputView.readMoney();
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(InvalidPurchaseAmountException.class);
    }

    @DisplayName("사용자가 구입 금액을 숫자가 아닌 다른 값으로 입력하면 예외 발생")
    @Test
    void inputInvalidMoney() {
        System.setIn(createUserInput("hello pobi"));
        assertThatThrownBy(InputView::readMoney)
                .isInstanceOf(NonNumericAmountException.class);
    }

    InputStream createUserInput(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }
}

