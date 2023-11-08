package lotto.util;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static lotto.util.NumbersValidator.*;

public class NumbersValidatorTest {

    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> validate(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> checkDuplicate(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외가 발생한다.")
    @Test
    void createLottoByOutOfRangeNumber() {
        assertThatThrownBy(() -> checkElementsInRange(List.of(1, 2, 6, 10, 25, 98)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("null 입력시 예외 발생")
    @Test
    void testExceptionThrownForNullInput() {
        String nullInput = "";

        assertThatThrownBy(() -> validateNumber(nullInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("공백 입력시 예외 발생")
    @Test
    void testExceptionThrownForWhitespaceInput() {
        String whiteSpaceInput = "1 2 3 4 5";

        assertThatThrownBy(() -> validateNumber(whiteSpaceInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("숫자가 아닌 값 입력시 예외 발생")
    @Test
    void testExceptionThrownForNotNumericInput() {
        String notNumericInput = "56000k";

        assertThatThrownBy(() -> validateNumber(notNumericInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호에 ,를 제외한 다른 숫자가 없을 시 예외 발생")
    @Test
    void testExceptionThrownForOnlyCommaInput() {
        String onlyCommaInput = ",,,,,";

        assertThatThrownBy(() -> validateLottoNumber(onlyCommaInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
