package lotto.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

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
}