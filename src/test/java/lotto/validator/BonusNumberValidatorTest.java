package lotto.validator;

import static lotto.config.BonusNumberErrorMessage.BONUS_NUMBER_ERROR_MESSAGE;
import static lotto.config.BonusNumberErrorMessage.BONUS_NUMBER_UNIQUE_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberValidatorTest {
    private void assertExceptionTest(BonusNumberValidator validator, String input, String message) {
        assertThatThrownBy(() -> validator.validate(input))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(message);
    }

    @Nested
    @DisplayName("보너스 번호 입력에 대한 검증")
    class create {
        @DisplayName("모든 검증 성공시 입력한 문자열 반환")
        @Test
        void success() {
            // given
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
            BonusNumberValidator validator = new BonusNumberValidator(numbers);
            String input = "30";
            // when
            String validated = validator.validate(input);
            // then
            assertThat(validated).isEqualTo(input);
        }

        @DisplayName("정해진 최대 최소 이내의 자연수 이외의 문자를 포함해서 입력시 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(strings = {"", "a", ",", "ab,", "포포포비", "12포", "0", "1.0", "1/1", "01", "999"})
        void fail_Numeric(String input) {
            // given
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
            BonusNumberValidator validator = new BonusNumberValidator(numbers);
            // when, then
            assertExceptionTest(validator, input, BONUS_NUMBER_ERROR_MESSAGE.getMessage());
        }

        @DisplayName("당첨 로또 번호와 중복된 값을 입력시 예외를 발생시킨다.")
        @Test
        void fail_Unique() {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
            BonusNumberValidator validator = new BonusNumberValidator(numbers);
            String input = "3";
            // when, then
            assertExceptionTest(validator, input, BONUS_NUMBER_UNIQUE_ERROR_MESSAGE.getMessage());
        }
    }
}