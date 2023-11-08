package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.model.lotto.LottoNumber;
import lotto.domain.model.lotto.Lotto;
import lotto.domain.model.lotto.LottoWinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class LottoWinningNumbersTest {

    @DisplayName("보너스 번호와 당첨번호에 중복되는 숫자가 있을 시 예외가 발생한다..")
    @Test
    void createLottoWinningNumbersByDuplicatedNumber(Integer number) {
        assertThatThrownBy(() -> new LottoWinningNumbers(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), LottoNumber.from(6)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}