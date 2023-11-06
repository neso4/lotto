package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.util.Utils;
import lotto.util.Validator;
import org.junit.jupiter.api.Test;

public class ControllerTest {

    @Test
    void validateLottoNumber_로또번호_쉼표로_나뉘어서_저장_안되면_예외반환() {
        assertThat(Validator.validateLottoNumber("1,2,3,4,5,6")).isEqualTo(List.of(1,2,3,4,5,6));
    }

    @Test
    void validateBonusNumber_보너스번호_숫자아니면_예외반환() {
        assertThatThrownBy(() -> Validator.validateBonusNumber("aaa")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateMoneyIsPositive_구입금액_음수면_예외반환() {
        assertThatThrownBy(() -> Validator.validateMoneyIsPositive(-10000)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateMoneyDivideBy1000_구입금액_1000으로_안나눠지면_예외반환() {
        assertThatThrownBy(() -> Validator.validateMoneyDivideBy1000(13555)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void parseInt_숫자반환_테스트() {
        assertThat(Utils.parseInt("123")).isEqualTo(123);
    }

    @Test
    void readLottoMoney_숫자_아니면_예외반환() {
        assertThatThrownBy(() -> Utils.validateStringIsNumber("abc")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void readLottoMoney_공백_이면_예외반환() {
        assertThatThrownBy(() -> Utils.validateHasInput(" ")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void readLottoMoney_null_이면_예외반환() {
        assertThatThrownBy(() -> Utils.validateHasInput(null)).isInstanceOf(IllegalArgumentException.class);
    }
}

