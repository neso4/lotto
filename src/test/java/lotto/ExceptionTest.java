package lotto;

import lotto.domain.Exception;
import lotto.view.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.domain.Exception.NumberCheck;
import static lotto.domain.Exception.overNumber;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExceptionTest {


    @Test
    @DisplayName("숫자인지 체크")
    void numberCheckTest() {
        assertThatThrownBy(() -> NumberCheck("일이이"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.INPUT_TYPE_ERROR.getExceptionValue());
    }

    @Test
    @DisplayName("1~45사이의 로또 숫자가 아닌 경우")
    void overNumberTest() {
        assertThatThrownBy(() ->overNumber(55))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.NOT_NUMBER_RANGE.getExceptionValue());
    }

    @DisplayName("중복값 체크")
    @Test
    void duplicationCheckBonusTest() {
        int bonus = 7;
        List<Integer> selectBonus = List.of(1, 2, 4, 5, 6, 7);
        assertThatThrownBy(() -> Exception.duplicationCheckBonus(bonus,selectBonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.NOT_NUMBER_OVERLAP.getExceptionValue());
    }

}