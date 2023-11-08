package lotto.validationTest;

import java.util.List;
import lotto.validation.BonusNumberInputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class BonusNumberInputValidatorTest {
    @DisplayName("1~45 숫자 정상 입력 테스트")
    @Test
    public void testValidBonusNumber() {
        String validBonusNumber = "7";

        assertThatCode(() -> BonusNumberInputValidator.validate(validBonusNumber, List.of(1, 2, 3, 4, 5, 6)))
                .doesNotThrowAnyException();
    }

    @DisplayName("시도 횟수가 0일 때 예외 처리")
    @Test
    public void testBonusNumberZero() {
        String zeroBonusNumber = "0";

        assertThatIllegalArgumentException().isThrownBy(
                        () -> BonusNumberInputValidator.validate(zeroBonusNumber, List.of(1, 2, 3, 4, 5, 6)))
                .withMessage("입력값이 0이 될 수 없습니다.");
    }

    @DisplayName("숫자가 아닌 입력에 대한 예외 처리")
    @Test
    public void testNonDigitBonusNumber() {
        String nonDigitBonusNumber = "abc";

        assertThatIllegalArgumentException().isThrownBy(
                        () -> BonusNumberInputValidator.validate(nonDigitBonusNumber, List.of(1, 2, 3, 4, 5, 6)))
                .withMessage("입력값이 숫자가 아닙니다.");
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복일 때 예외 처리")
    @Test
    public void testDuplicateBonusNumber() {
        String duplicateBonusNumber = "5"; // 예시로 중복된 번호를 테스트

        assertThatIllegalArgumentException().isThrownBy(
                        () -> BonusNumberInputValidator.validate(duplicateBonusNumber, List.of(1, 2, 3, 4, 5, 6)))
                .withMessage("보너스 번호와 당첨 번호는 달라야 합니다.");
    }
}
