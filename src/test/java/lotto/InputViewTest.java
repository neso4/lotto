package lotto;

import lotto.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class InputViewTest {

    @DisplayName("로또 구매 입력값이 없을 경우 예외 처리")
    @Test
    void emptyMoneyInput() {
        //given
        InputView inputView = new InputView();

        //when
        String moneyInput = "";

        //then
        assertThatThrownBy(() -> inputView.validateCustomerMoneyInput(moneyInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력값이 없습니다.")
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("로또 구매 입력값이 숫자가 아닐 경우 예외 처리")
    @Test
    void validateDigitMoneyInput() {
        //given
        InputView inputView = new InputView();

        //when
        String englishInMoney = "100a";
        String koreanInMoney = "100ㅁ";
        String specialSignInMoney = "100%";

        //then
        assertThatThrownBy(() -> inputView.validateCustomerMoneyInput(englishInMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 숫자만 입력해주세요.")
                .hasMessageContaining("[ERROR]");

        assertThatThrownBy(() -> inputView.validateCustomerMoneyInput(koreanInMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 숫자만 입력해주세요.")
                .hasMessageContaining("[ERROR]");

        assertThatThrownBy(() -> inputView.validateCustomerMoneyInput(specialSignInMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 숫자만 입력해주세요.")
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("올바른 로또 구매 입력값 처리")
    @Test
    void parseMoneyInput() {
        //given
        InputView inputView = new InputView();

        //when
        String moneyInput = "1000";
        long money = inputView.validateCustomerMoneyInput(moneyInput);

        //then
        long expectedMoney = 1000;
        assertThat(money).isEqualTo(expectedMoney);
    }

    @DisplayName("당첨 번호 입력값이 없을 경우 예외 처리")
    @Test
    void emptyWinningNumbers() {
        //given
        InputView inputView = new InputView();

        //when
        String winningNumberInput = "";

        //then
        assertThatThrownBy(() -> inputView.validateWinningNumberInput(winningNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력값이 없습니다.")
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("당첨 번호 입력 값이 \',\'로 구분되어 있지 않을 경우")
    @Test
    void validateDelimiterWinningNumberInput() {
        //given
        InputView inputView = new InputView();

        //when
        String winningNumberInput = "123456";

        //then
        assertThatThrownBy(() -> inputView.validateWinningNumberInput(winningNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] ','로 구분해주시길 바랍니다.")
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("당첨 번호 입력값이 숫자가 아닌 경우 예외 처리")
    @Test
    void winningNumberNotDigit() {
        //given
        InputView inputView = new InputView();

        //when
        String winningNumbersInput = "1,2,3,4,5,a";

        //then
        assertThatThrownBy(() -> inputView.validateEachWinningNumberInput(winningNumbersInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 숫자여야 합니다.")
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("올바른 당첨 번호 처리")
    @Test
    void parseWinningNumber() {
        //given
        InputView inputView = new InputView();

        //when
        String winningNumberInput = "1,2,3,4,5,6";
        String[] winningNumber = inputView.validateWinningNumberInput(winningNumberInput);

        //then
        String[] expectedWinningNumber = {"1", "2", "3", "4", "5", "6"};
        assertThat(winningNumber.length).isEqualTo(6);
        assertThat(winningNumber).isEqualTo(expectedWinningNumber);
    }

    @DisplayName("보너스 번호 입력값이 없을 경우 예외 처리")
    @Test
    void emptyBonusNumberInput(){
        //given
        InputView inputView = new InputView();

        //when
        String bonusNumberInput = "";

        //then
        assertThatThrownBy(() -> inputView.validateBonusNumberInput(bonusNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력값이 없습니다.")
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("보너스 번호 입력값이 숫자가 아닐 경우 예외 처리")
    @Test
    void validateDigitBonusNumber() {
        //given
        InputView inputView = new InputView();

        //when
        String englishInBonusNumber = "a";
        String koreanInBonusNumber = "ㅁ";
        String specialSignInBonusNumber = "%";

        //then
        assertThatThrownBy(() -> inputView.validateBonusNumberInput(englishInBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 숫자여야 합니다.")
                .hasMessageContaining("[ERROR]");

        assertThatThrownBy(() -> inputView.validateBonusNumberInput(koreanInBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 숫자여야 합니다.")
                .hasMessageContaining("[ERROR]");

        assertThatThrownBy(() -> inputView.validateBonusNumberInput(specialSignInBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 숫자여야 합니다.")
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("올바른 보너스 번호 입력값 처리")
    @Test
    void parseBonusNumber() {
        //given
        InputView inputView = new InputView();

        //when
        String bonusNumberInput = "45";
        int bonusNumber = inputView.validateBonusNumberInput(bonusNumberInput);

        //then
        int expectedBonusNumber = 45;
        assertThat(bonusNumber).isEqualTo(expectedBonusNumber);
    }
}
