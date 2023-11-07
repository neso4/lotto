package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    @DisplayName("빈값 입력 시 예외 발생")
    @Test
    void isInputEmpty() throws IllegalArgumentException{

        String userInput = "";

        assertThat(Validator.isInputEmpty(userInput)).isTrue();
    }

    @DisplayName("구분자 쉽표(,) 이외 문자 입력 시 예외 발생")
    @Test
    void isValidCharacter() {
        String userInput = "1,2\\.3,4,5,6";
        assertThat(Validator.isValidCharacter(userInput)).isTrue();
    }

    @DisplayName("입력값에 숫자 이외의 문자 입력 시 예외 발생")
    @Test
    void isNumberOnly() {
        String userInput = "a,b,c,d,e,6";
        assertThat(Validator.isNumberOnly(userInput)).isTrue();
    }

    @DisplayName("로또 구입 금액이 1,000원 미만일 경우 예외 발생")
    @Test
    void isPriceUnder1000() {
        Integer inputBudget = 900;
        assertThat(Validator.isPriceUnder1000(inputBudget)).isTrue();

    }

    @Test
    void isPriceIndivisible() {
        Integer inputBudget = 12001;
        assertThat(Validator.isPriceIndivisible(inputBudget)).isTrue();
    }

    @Test
    void isNumberOnValidRange() {
        Integer winningNumber = 46;
        assertThat(Validator.isNumberOnValidRange(winningNumber)).isTrue();
    }

    @Test
    void isWinningNumberDuplicate() {
        Integer checkingNumber = 5;
        List<Integer> referenceNumbers =List.of(5, 10, 15, 20, 5, 30);
        assertThat(Validator.isWinningNumberDuplicate(checkingNumber, referenceNumbers)).isTrue();
    }

    @Test
    void isBonusNumberDuplicate() {
        Integer bonusNumber = 12;
        List<Integer> winningNumbers =List.of(6, 12, 18, 24, 30, 36);
        assertThat(Validator.isBonusNumberDuplicate(bonusNumber, winningNumbers)).isTrue();
    }

    @Test
    void isNumberCountOutOfRange() {
        List<Integer> winningNumbers = List.of(6, 12, 18, 24, 42);
        assertThat(Validator.isNumberCountOutOfRange(winningNumbers)).isTrue();
    }
}