package lotto.utils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @Test
    void validate_빈문자열받으면_예외를_반환한다() {
        String empty = "";
        assertThatThrownBy(() -> InputValidator.validate(empty))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validate_null받으면_예외를_반환한다() {
        assertThatThrownBy(() -> InputValidator.validate(null))
                .isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", " ", "            "})
    void validate_공백문자열_받으면_예외를_반환한다(String blank) {
        assertThatThrownBy(() -> InputValidator.validate(blank))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"8,000", "4,000", "10,000"})
    void validateMoney는_쉼표있는_문자열은_검증_통과한다(String input) {
        // when
        String result = InputValidator.validateMoney(input);
        // then
        assertThat(result).isEqualTo(input.replace(",", ""));
    }

    @ParameterizedTest
    @ValueSource(strings = {"8s000", "'4000s", " "})
    void validateMoney는_정수가아닌_문자열에_예외를_반환한다(String input) {
        assertThatThrownBy(() -> InputValidator.validateMoney(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "100000000"})
    void validateMoney는_최소_최대범위를_벗어난_문자열에_예외를_반환한다(String input) {
        assertThatThrownBy(() -> InputValidator.validateMoney(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"8,0", "'40s", " "})
    void validateLottoNumber는_정수가아닌_문자열에_예외를_반환한다(String input) {
        assertThatThrownBy(() -> InputValidator.validateLottoNumber(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6", "12,13,45,6,3,2"})
    void validateWinnings는_당첨번호를_검증통과시킨다(String input) {
        // when
        String result = InputValidator.validateWinnings(input);
        // then
        assertThat(result).isEqualTo(input);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,1,3,4,5,6", "12,13,45,13,3,2"})
    void validateWinnings는_중복된숫자가_포함된_문자열에_예외를_반환한다(String input) {
        assertThatThrownBy(() -> InputValidator.validateWinnings(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,1,3,,4,5, ,6", "12,13,45,:,3,2"})
    void validateWinnings는_정수외에_포함된_문자열에_예외를_반환한다(String input) {
        assertThatThrownBy(() -> InputValidator.validateWinnings(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}