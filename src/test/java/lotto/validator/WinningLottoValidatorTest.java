package lotto.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.validator.AssertException.assertExceptionTest;
import static org.assertj.core.api.Assertions.assertThat;


class WinningLottoValidatorTest {
    private static final String WINNING_LOTTO_COMMA_ERROR_MESSAEGE =
            "[ERROR] 첫 번째 또는 마지막 문자의 콤마 또는 연속된 콤마는 입력할 수 없습니다.";
    private static final String NUMERIC_ERROR_MESSAGE =
            "[ERROR] 1부터 45까지의 자연수와 콤마만 입력할 수 있습니다.";

    private static final String WINNING_LOTTO_UNIQUE_ERROR_MESSAGE = "[ERROR] 중복된 번호는 입력할 수 없습니다.";

    @Nested
    @DisplayName("로또 당첨 번호 입력에 대한 검증")
    class create{

        @DisplayName("모든 검증 통과시 입력한 문자열 반환")
        @Test
        void success() {
            // give
            Validator<String> validator = new WinningLottoValidator();
            String input = "1,2,10,18,42,45";
            // when
            String validated = validator.validate(input);
            // then
            assertThat(validated).isEqualTo(input);
        }

        @DisplayName("자연수나 콤마를 제외한 값을 입력시 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(strings = {"1\n,2,3,4,5,6", "포비,로또1등,당첨,기원,굿,굿","Lisa,Lotto,1st,prize,good,good",
        "!@!@#1,2,3,@!*&", "1 , 2, 3, 4, 5, 6", "-1,2,3,4,5,6", "1.0,2,3,4,5,6", "1+1,2,3,4,5,6"})
        void fail_NumericWithComma(String input) {
            // given
            Validator<String> validator = new WinningLottoValidator();
            // when, then
            assertExceptionTest(validator, input, NUMERIC_ERROR_MESSAGE);
        }

        @DisplayName("최대 최소 범위를 넘은 숫자를 입력시 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(strings = {"0,1,2,3,4,5", "1,2,3,4,5,46"})
        void fail_NumericMinToMax(String input) {
            // given
            Validator<String> validator = new WinningLottoValidator();
            // when, then
            assertExceptionTest(validator, input, NUMERIC_ERROR_MESSAGE);
        }

        @DisplayName("6개보다 적거나 많은 로또 번호 입력시 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(strings = {"1,2,3", "1,2,3,4,5,6,7"})
        void fail_Length(String input) {
            // given
            Validator<String> validator = new WinningLottoValidator();
            // when, then
            assertExceptionTest(validator, input, NUMERIC_ERROR_MESSAGE);
        }

        @DisplayName("연속된 콤마 입력시 예외를 발생시킨다.")
        @Test
        void fail_NotStraightComma() {
            // given
            Validator<String> validator = new WinningLottoValidator();
            String input = "1,,2,,3,,,,,4,,,5,,6";
            // when, then
            assertExceptionTest(validator, input, WINNING_LOTTO_COMMA_ERROR_MESSAEGE);
        }

        @DisplayName("맨 처음 또는 맨 마지막에 콤마 입력시 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(strings = {",1,2,3,4,5,6", "1,2,3,4,5,6,"})
        void fail_NotStartsOrEndsWithComma(String input) {
            // given
            Validator<String> validator = new WinningLottoValidator();
            // when, then
            assertExceptionTest(validator, input, WINNING_LOTTO_COMMA_ERROR_MESSAEGE);
        }

        @DisplayName("중복된 번호 입력시 예외를 발생시킨다.")
        @Test
        void fail_Unique() {
            // given
            Validator<String> validator = new WinningLottoValidator();
            String input = "1,4,10,14,25,25";
            //when, then
            assertExceptionTest(validator, input, WINNING_LOTTO_UNIQUE_ERROR_MESSAGE);
        }
    }
}