package lotto.model;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {

    @Test
    @DisplayName("5등 두번 당첨 시 총 만원 반환해야한다")
    void calculatePriceTest() {
        // given
        Lotto lotto1 = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = Lotto.from(List.of(1, 2, 3, 4, 7, 8));
        LottoStorage lottoStorage = LottoStorage.from(List.of(lotto1, lotto2));
        List<Integer> winningNumbers = List.of(1, 2, 3, 40, 41, 42);
        Integer bonusNumber = 43;
        WinningNumbers winningNumber = WinningNumbers.of(Lotto.from(winningNumbers), bonusNumber);
        // when
        LottoResult lottoResult = LottoResult.of(lottoStorage, winningNumber);
        long price = lottoResult.calculatePriceSum();
        // then
        Assertions.assertThat(price).isEqualTo(10000);
    }

    @Test
    @DisplayName("5등 두번 당첨 시 총 5등 당첨 횟수는 2번이어야한다.")
    void getRankCountTest() {
        // given
        Lotto lotto1 = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = Lotto.from(List.of(1, 2, 3, 4, 7, 8));
        LottoStorage lottoStorage = LottoStorage.from(List.of(lotto1, lotto2));
        List<Integer> winningNumbers = List.of(1, 2, 3, 40, 41, 42);
        Integer bonusNumber = 43;
        WinningNumbers winningNumber = WinningNumbers.of(Lotto.from(winningNumbers), bonusNumber);
        // when
        LottoResult lottoResult = LottoResult.of(lottoStorage, winningNumber);
        int count = lottoResult.getRankCount(LottoRank.FIFTH);
        // then
        Assertions.assertThat(count).isEqualTo(2);
    }
}