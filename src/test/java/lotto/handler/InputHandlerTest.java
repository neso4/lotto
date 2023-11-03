package lotto.handler;

import lotto.exception.InvalidPurchaseAmountException;
import lotto.exception.InvalidWinningNumberException;
import lotto.exception.NullInputException;
import lotto.exception.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class InputHandlerTest {

    private InputHandler inputHandler;

    @BeforeEach
    void beforeEach() {
        this.inputHandler = new InputHandler();
    }

    @DisplayName("로또 구입 금액 검증 통과 테스트")
    @Test
    void handlePurchaseAmount() {
        assertThat(inputHandler.handlePurchaseAmount("1000")).isEqualTo(1);
    }

    @DisplayName("로또 구입 금액 입력값이 아닐때 예외 발생")
    @Test
    void handleStringPurchaseAmountTest() {
        assertThatThrownBy(() -> inputHandler.handlePurchaseAmount("fail"))
                .isInstanceOf(ParseException.class);
    }

    @DisplayName("로또 구입 금액 입력값이 공백일때 예외 발생")
    @Test
    void handleBlankLPurchaseAmountTest() {
        assertThatThrownBy(() -> inputHandler.handlePurchaseAmount(""))
                .isInstanceOf(NullInputException.class);
    }

    @DisplayName("로또 구입 금액 입력값이 1000원으로 나누어지지 않을때 예외 발생")
    @Test
    void handleNegativePurchaseAmountTest() {
        assertThatThrownBy(() -> inputHandler.handlePurchaseAmount("1001"))
                .isInstanceOf(InvalidPurchaseAmountException.class);
    }

    @DisplayName("당첨 번호 검증 통과 테스트")
    @Test
    void handleWinningNumbersTest() {
        assertThat(inputHandler.handleWinningNumbers("1,2,3,4,5,6").size())
                .isEqualTo(6);
    }

    @DisplayName("당첨 번호 입력값이 공백일때 예외 발생")
    @Test
    void handleBlankWinningNumbersTest() {
        assertThatThrownBy(() -> inputHandler.handleWinningNumbers(""))
                .isInstanceOf(NullInputException.class);
    }

    @DisplayName("당첨 번호 입력값이 숫자가 아닐때 예외 발생")
    @Test
    void handleStringWinningNumbersTest() {
        assertThatThrownBy(() -> inputHandler.handleWinningNumbers("a,b,c,d,e,f,g"))
                .isInstanceOf(ParseException.class);
    }

    @DisplayName("당첨 번호 입력값이 유효한 값이 아닐때 예외 발생")
    @Test
    void handleInvalidWinningNumbersTest() {
        assertThatThrownBy(() -> inputHandler.handleWinningNumbers("-1,-2,-3,-4,-5,-6"))
                .isInstanceOf(InvalidWinningNumberException.class);
    }
}