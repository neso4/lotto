package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoPriceTest {
    @DisplayName("정수형태가 아닌 문자열을 입력하면 예외가 발생")
    @ParameterizedTest(name = "{displayName}({arguments})")
    @ValueSource(strings = {"ABC", "12L", "13.5"})
    void createLottoPriceByWrongString(String numericString) {
        assertThatThrownBy(() -> new LottoPrice(numericString))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1000 미만의 숫자를 입력하면 예외가 발생")
    @ParameterizedTest(name = "{displayName}({arguments})")
    @ValueSource(strings = {"999", "500", "0", "-500"})
    void createLottoPriceByWrongRange(String numericString) {
        assertThatThrownBy(() -> new LottoPrice(numericString))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1000으로 나누어 떨어지지 않는 숫자를 입력하면 예외가 발생")
    @Test
    void createLottoByOverRangeNumber() {
        assertThatThrownBy(() -> new LottoPrice("2050"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력한 가격에 맞는 시도횟수를 반환하는지 확인")
    @ParameterizedTest(name = "{displayName}({0})")
    @CsvSource(value = {"52000,52", "3000,3", "720000,720"})
    void checkLottoTryCount(String input, int answer) {
        int tryCount = new LottoPrice(input).getTryCount();
        assertThat(tryCount).isEqualTo(answer);
    }
}
