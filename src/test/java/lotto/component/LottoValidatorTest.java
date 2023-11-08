package lotto.component;

import static lotto.exception.ExceptionMessage.DUPLICATED_LOTTO_NUMBERS;
import static lotto.exception.ExceptionMessage.INVALID_LOTTO_NUMBER_RANGE;
import static lotto.exception.ExceptionMessage.INVALID_LOTTO_NUMBER_SIZE;
import static lotto.exception.ExceptionMessage.INVALID_PURCHASE_AMOUNT_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoValidatorTest {

    @Test
    void 로또_구입_금액이_올바르면_검증을_통과한다() {
        //Arrange
        String purchaseAmountInput = "1000";
        LottoValidator validator = new LottoValidator();

        //Act //Assert
        validator.verifyPurchaseAmount(purchaseAmountInput);
    }

    @CsvSource(value = {"-1", "0", "1234", "' '", "abcd"})
    @ParameterizedTest
    void 로또_구입_금액이_1000의_배수가_아니면_예외가_발생한다(String purchaseAmount) {
        //Arrange
        LottoValidator validator = new LottoValidator();

        //Act //Assert
        assertThatThrownBy(() -> validator.verifyPurchaseAmount(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_PURCHASE_AMOUNT_MESSAGE.getMessage());
    }

    @Test
    void 로또_당첨_숫자가_올바르면_검증을_통과한다() {
        //Arrange
        String lottoWinNumbersInput = "1,2,3,4,5,6";
        LottoValidator validator = new LottoValidator();

        //Act //Assert
        validator.verifyWinNumbers(lottoWinNumbersInput);
    }

    @Test
    void 로또_당첨_숫자가_중복되면_예외가_발생한다() {
        //Arrange
        String lottoWinNumbersInput = "1,2,3,4,5,5";
        LottoValidator validator = new LottoValidator();

        //Act //Assert
        assertThatThrownBy(() -> validator.verifyWinNumbers(lottoWinNumbersInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DUPLICATED_LOTTO_NUMBERS.getMessage());
    }

    @Test
    void 로또_당첨_숫자가_6개_미만이면_예외가_발생한다() {
        //Arrange
        String lottoWinNumbersInput = "1,2,3,4,5";
        LottoValidator validator = new LottoValidator();

        //Act //Assert
        assertThatThrownBy(() -> validator.verifyWinNumbers(lottoWinNumbersInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_LOTTO_NUMBER_SIZE.getMessage());
    }

    @Test
    void 로또_당첨_숫자의_범위가_올바르지_않으면_예외가_발생한다() {
        //Arrange
        String lottoWinNumbersInput = "1,2,3,4,5,46";
        LottoValidator validator = new LottoValidator();

        //Act //Assert
        assertThatThrownBy(() -> validator.verifyWinNumbers(lottoWinNumbersInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_LOTTO_NUMBER_RANGE.getMessage());
    }

    @CsvSource(value = {"abcdefg", "' ", "a,b,c,d,e,f"})
    @ParameterizedTest
    void 당첨_숫자가_올바르지_않으면_예외가_발생한다(String lottoWinNumbersInput) {
        //Arrange
        LottoValidator validator = new LottoValidator();

        //Act //Assert
        assertThatThrownBy(() -> validator.verifyWinNumbers(lottoWinNumbersInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_LOTTO_NUMBER_SIZE.getMessage());
    }

    @Test
    void 보너스_숫자가_올바르면_검증을_통과한다() {
        //Arrange
        String bonusNumberInput = "7";
        List<Integer> winNumbers = List.of(1, 2, 3, 4, 5, 6);
        LottoValidator validator = new LottoValidator();

        //Act //Assert
        validator.verifyBonusNumber(winNumbers, bonusNumberInput);
    }

    @CsvSource(value = {"-1", "0", "' '", "a"})
    @ParameterizedTest
    void 보너스_숫자가_올바르지_않으면_예외가_발생한다(String bonusNumberInput) {
        //Arrange
        List<Integer> winNumbers = List.of(1, 2, 3, 4, 5, 6);
        LottoValidator validator = new LottoValidator();

        //Act //Assert
        assertThatThrownBy(() -> validator.verifyBonusNumber(winNumbers, bonusNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_LOTTO_NUMBER_RANGE.getMessage());
    }

    @Test
    void 보너스_숫자가_중복이면_예외가_발생한다() {
        //Arrange
        String bonusNumberInput = "6";
        List<Integer> winNumbers = List.of(1, 2, 3, 4, 5, 6);
        LottoValidator validator = new LottoValidator();

        //Act //Assert
        assertThatThrownBy(() -> validator.verifyBonusNumber(winNumbers, bonusNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DUPLICATED_LOTTO_NUMBERS.getMessage());
    }
}