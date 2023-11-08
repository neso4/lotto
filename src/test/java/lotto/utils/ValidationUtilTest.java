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
    void validateIsBonusNumberDuplicated() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ValidationUtil.validateIsBonusNumberDuplicated(List.of(1, 2, 3, 4, 5, 6), 6))
                .withMessage("해당 번호는 보너스 번호로 사용할 수 없습니다. 이미 로또 번호 안에 존재합니다.");
    }

    @Test
    void validateHasSixNumbers() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ValidationUtil.validateHasSixNumbers(List.of(1, 2, 3, 4, 5, 6, 7)))
                .withMessage("로또 번호의 개수는 6개여야 합니다.");
    }

    @Test
    void validateIsAllDigit() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ValidationUtil.validateIsAllDigit(List.of("a", "1", "2", "3", "4", "5")))
                .withMessage("로또 번호는 숫자여야 합니다.");
    }

    @Test
    void validateIsDigit() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ValidationUtil.validateIsDigit("a"))
                .withMessage("로또 번호는 숫자여야 합니다.");
    }

    @Test
    void validateNumberRange() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ValidationUtil.validateNumberInRange(46))
                .withMessage("로또 번호는 1과 45 사이여야 합니다.");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> ValidationUtil.validateNumberInRange(0))
                .withMessage("로또 번호는 1과 45 사이여야 합니다.");
    }
}
