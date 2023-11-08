package lotto.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoWinning;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoWinningRepositoryTest {
    @Test
    @DisplayName("당첨 로또가 정상적으로 저장되는지 테스트")
    void saveWinningLotto(){
        LottoWinningRepository lottoWinningRepository = new LottoWinningRepository();

        lottoWinningRepository.saveWinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        lottoWinningRepository.saveBonusNumber(7);

        LottoWinning savingLotto = lottoWinningRepository.getLottoWinning();

        assertEquals(List.of(1, 2, 3, 4, 5, 6), savingLotto.getNumbers());
        assertEquals(7, savingLotto.getBonusNumber());
    }
}