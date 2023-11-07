package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumberTest {

    @DisplayName("보너스번호와 당첨번호가 일치하면 예외발생")
    @Test
    void testWinningNumberCreationWithDuplicateBonusNumber() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> new WinningNumber(lotto, (1))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨번호와 내 로또번호를 비교하여 일치하는 숫자의 갯수 카운트")
    void testLottoNumberMatching() {
        Lotto myLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningNumber winLotto = new WinningNumber(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), 7);
        int matchCount = winLotto.getMatchCount(myLotto);
        Assertions.assertEquals(6, matchCount);

    }

    @Test
    @DisplayName("로또 등수가 정상적으로 반환되는지 확인")
    void calculateLottoRank(){
        Lotto myLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningNumber winLotto = new WinningNumber(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), 7);
        LottoRank lottoRank = winLotto.getLottoRank(myLotto);
        Assertions.assertEquals(LottoRank.FIRST,lottoRank);
    }

}