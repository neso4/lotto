package lotto.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.enums.ErrorMessages;
import org.junit.jupiter.api.Test;

public class InputValidatorTest {
    private final InputValidator validator = new InputValidator();

    @Test
    void 금액을_입력하지_않거나_공백_으로_입력한_경우() {
        assertThatThrownBy(() -> validator.validateMoney(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessages.INPUT_EMPTY_EXCEPTION_MSG.getMsg());
        assertThatThrownBy(() -> validator.validateMoney(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessages.INPUT_BLANK_EXCEPTION_MSG.getMsg());
    }

    @Test
    void 금액에_숫자가_아닌_문자가_포함되어_있는_경우() {
        assertThatThrownBy(() -> validator.validateMoney("123a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessages.NON_NUMBER_EXCEPTION_MSG.getMsg());
    }

    @Test
    void 금액이_1000원_단위로_나누어_떨어지지_않는_경우() {
        assertThatThrownBy(() -> validator.validateMoney("2500"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessages.INPUT_DIVIDE_EXCEPTION_MSG.getMsg());
    }

    @Test
    void 당첨_번호를_입력하지_않거나_공백을_입력한_경우() {
        assertThatThrownBy(() -> validator.validateHitNumbers(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessages.INPUT_EMPTY_EXCEPTION_MSG.getMsg());
        assertThatThrownBy(() -> validator.validateHitNumbers(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessages.INPUT_BLANK_EXCEPTION_MSG.getMsg());
    }

    @Test
    void 당첨_번호에_숫자가_아닌_문자가_존재할_경우() {
        assertThatThrownBy(() -> validator.validateHitNumbers("1,a,2,3,31,45"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessages.NON_NUMBER_EXCEPTION_MSG.getMsg());
    }

    @Test
    void 당첨_번호를_정해진_형식으로_입력하지_않은_경우() {
        assertThatThrownBy(() -> validator.validateHitNumbers("123456"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessages.ANSWER_FORM_EXCEPTION_MSG.getMsg());
    }

    @Test
    void 보너스_번호를_입력하지_않거나_공백을_입력한_경우() {
        assertThatThrownBy(() -> validator.validateBonusNumber(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessages.INPUT_EMPTY_EXCEPTION_MSG.getMsg());
        assertThatThrownBy(() -> validator.validateBonusNumber(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessages.INPUT_BLANK_EXCEPTION_MSG.getMsg());
    }

    @Test
    void 보너스_번호에_숫자가_아닌_문자를_입력한_경우() {
        assertThatThrownBy(() -> validator.validateBonusNumber("ab"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessages.NON_NUMBER_EXCEPTION_MSG.getMsg());
    }
}
