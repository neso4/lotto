package lotto.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BonusNumberValidatorTest {
    @Nested
    @DisplayName("inputValidate 메소드 test")
    class ValidateInputBonusNumberTest {
        @DisplayName("보너스 번호로 숫자가 들어오면 검증 통과")
        @Test
        void Bonus_number_consists_of_numbers() {
            // given
            String input1 = "1";
            String input100 = "100";
            String input10000 = "10000";

            // when
            // then
            BonusNumberValidator.inputValidate(input1);
            BonusNumberValidator.inputValidate(input100);
            BonusNumberValidator.inputValidate(input10000);
        }

        @DisplayName("보너스 숫자가 입력되지 않은 경우 예외 발생")
        @Test
        void Purchase_amount_is_empty() {
            // given
            String input = "";

            // when
            // then
            assertThatThrownBy(() -> BonusNumberValidator.inputValidate(input)).isInstanceOf(
                    IllegalArgumentException.class).hasMessage(ErrorMessage.ENTER_VALUE_MESSAGE.getMessage());
        }

        @DisplayName("보너스 번호로 숫자 외에 입력이 들어오면 예외 발생")
        @Test
        void Bonus_numbers_contain_characters_except_numbers() {
            // given
            String input = "A";

            // when
            // then
            assertThatThrownBy(() -> BonusNumberValidator.inputValidate(input)).isInstanceOf(
                            IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_INPUT_CHARACTER_MESSAGE.getMessage());
        }
    }

    @Nested
    @DisplayName("validateBonusNumber 메소드 test")
    class ValidateBonusNumberTest {
        private WinningLotto winningLotto;

        @BeforeEach
        void beforeEach() {
            winningLotto = new WinningLotto(List.of(11, 23, 27, 31, 38, 43));
        }

        @DisplayName("번호가 " + Lotto.MIN_LOTTO_NUMBER + " ~ " + Lotto.MAX_LOTTO_NUMBER + " 범위에 있으면 검증 통과")
        @Test
        void Numbers_in_range_lotto_range() {
            // given
            int number1 = 1;
            int number2 = 25;
            int number3 = 45;

            // when
            // then
            BonusNumber bonusNumber1 = new BonusNumber(number1, winningLotto);
            BonusNumber bonusNumber2 = new BonusNumber(number2, winningLotto);
            BonusNumber bonusNumber3 = new BonusNumber(number3, winningLotto);
        }

        @DisplayName("번호가 " + Lotto.MIN_LOTTO_NUMBER + " ~ " + Lotto.MAX_LOTTO_NUMBER + " 범위를 벗어나면 예외 발생")
        @Test
        void Numbers_out_of_range_lotto_range() {
            // given
            int number1 = -10;
            int number2 = 0;
            int number3 = 46;

            // when
            // then
            assertThatThrownBy(() -> new BonusNumber(number1, winningLotto)).isInstanceOf(
                            IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_LOTTO_BONUS_NUMBER_RANGE_MESSAGE.getMessage());
            assertThatThrownBy(() -> new BonusNumber(number2, winningLotto)).isInstanceOf(
                            IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_LOTTO_BONUS_NUMBER_RANGE_MESSAGE.getMessage());
            assertThatThrownBy(() -> new BonusNumber(number3, winningLotto)).isInstanceOf(
                            IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_LOTTO_BONUS_NUMBER_RANGE_MESSAGE.getMessage());
        }
    }
}