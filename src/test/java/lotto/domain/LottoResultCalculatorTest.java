package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class LottoResultCalculatorTest {
    WinningNumbers winningNumbers;
    BonusNumber bonusNumber;

    @DisplayName("당첨번호에 보너스넘버가 중복되지 않으면 정상 처리된다.")
    @Test
    void create() {
        // given
        winningNumbers = WinningNumbers.create("1,2,3,4,5,6");
        bonusNumber = BonusNumber.create("7");

        // when
        LottoResultCalculator lottoResultCalculator = LottoResultCalculator.create(winningNumbers, bonusNumber);

        // then
        assertThat(lottoResultCalculator).isNotNull();
    }

    @DisplayName("당첨번호와 보너스넘버가 중복되면 예외 발생한다.")
    @Test
    void cannotCreate_duplicate() {
        // given
        winningNumbers = WinningNumbers.create("1,2,3,4,5,6");
        bonusNumber = BonusNumber.create("1");

        // when, then
        assertThatThrownBy(() -> LottoResultCalculator.create(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("")
    @Test
    void calculateRanking() {
        // given
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        winningNumbers = WinningNumbers.create("1,2,3,4,5,6");
        bonusNumber = BonusNumber.create("7");
        LottoResultCalculator lottoResultCalculator = LottoResultCalculator.create(winningNumbers, bonusNumber);

        // when, then
//        assertThat(lottoResultCalculator.countMatchingNumbers(new Lotto(numbers))).isEqualTo(6);  //일치하는 번호 개수 리턴
//        assertThat(lottoResultCalculator.is)
    }

    @DisplayName("")
    @Test
    void calculateProfit() {
        // given

        // when

        // then

    }

}