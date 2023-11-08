package lotto.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class ValidationUtilTest {
    @Test
    void validateThousandUnit() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ValidationUtil.validateIsMoneyThousandUnit("2222"))
                .withMessage("로또 구입 금액은 1000원 단위여야 합니다.");
    }

    @Test
    void validateIsMoneyDigit() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ValidationUtil.validateIsMoneyDigit("notDigit"))
                .withMessage("로또 구입 금액은 숫자여야 합니다.");
    }

    @Test
    void validateNoDuplicatedNumbers() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ValidationUtil.validateNoDuplicatedNumbers(List.of(1, 2, 3, 4, 5, 5)))
                .withMessage("로또 번호는 서로 중복되지 않은 숫자여야 합니다.");
    }

    @Test
    void validateHasSixNumbers() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ValidationUtil.validateHasSixNumbers(List.of(1, 2, 3, 4, 5, 6, 7)))
                .withMessage("로또 번호의 개수는 6개여야 합니다.");
    }
}