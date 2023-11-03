package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InputValidationTest {
    @Test
    void 입력받은_문자열이_숫자가_아니면_예외_처리() {
        assertThatThrownBy(() -> InputValidation.checkNumber("1000a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 숫자가 아닙니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"0", "1001"})
    void 숫자가_나누어_떨어지지_않으면_예외_처리(int number) {
        assertThatThrownBy(() -> InputValidation.checkDivisible(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 숫자가 나누어 떨어지지 않습니다.");
    }
}