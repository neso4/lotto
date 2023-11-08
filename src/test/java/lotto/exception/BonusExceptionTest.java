package lotto.exception;

import lotto.model.BonusNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static lotto.exception.BonusException.bonusException;

public class BonusExceptionTest {
    @DisplayName("잘못된 보너스 숫자 예외 테스트")
    @Test
    void checkBonusNumberExceptionTest() {
        List<Integer> numbers = new ArrayList<>(List.of(1,2,3,4,5,6));
        int wrongBonusNumber1 = 1;
        int wrongBonusNumber2 = 0;
        int wrongBonusNumber3 = 46;

        Assertions.assertThatThrownBy(() -> bonusException(numbers, wrongBonusNumber1))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> bonusException(numbers, wrongBonusNumber2))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> bonusException(numbers, wrongBonusNumber3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("올바른 보너스 숫자 테스트")
    @Test
    void checkRightBonusNumberExceptionTest() {
        List<Integer> numbers = new ArrayList<>(List.of(1,2,3,4,5,6));
        int rightBonusNumber = 7;

        BonusNumber resultBonus = bonusException(numbers, rightBonusNumber);
        BonusNumber bonusNumber = new BonusNumber(7);

        Assertions.assertThat(resultBonus.getBonusNumber()).isEqualTo(bonusNumber.getBonusNumber());
    }
}
