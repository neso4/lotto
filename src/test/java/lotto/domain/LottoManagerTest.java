package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoManagerTest {

    private final int bonusNumber = 7;
    private final LottoManager lottoManager = new LottoManager(List.of(1,2,3,4,5,6), bonusNumber);

    private final List<Lotto> lottos = List.of(
            new Lotto(List.of(1, 2, 3, 4, 5, 6)),
            new Lotto(List.of(1, 2, 3, 4, 5, 7)),
            new Lotto(List.of(1, 2, 3, 4, 5, 7)),
            new Lotto(List.of(1, 2, 3, 4, 5, 8))
    );

    @DisplayName("로또들에 대한 등수와 로또들이 몇 등에 몇 개 당첨 됐는지 알 수 있다.")
    @Test
    void checkLottoRanks() {
        Map<Rank,Integer> ranks = lottoManager.calculateLottosRanks(lottos);

        assertAll("Lotto Ranks",
                () -> assertEquals(1, ranks.get(Rank.FIRST)),
                () -> assertEquals(2, ranks.get(Rank.SECOND)),
                () -> assertEquals(1, ranks.get(Rank.THIRD))
        );
    }

    @DisplayName("보너스 숫자가 1과 45사이가 아니면 예외가 발생한다")
    @Test
    void checkBonusNumberRange() {
        assertThatThrownBy(() -> new LottoManager(List.of(1,2,3,4,5,6), 100))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
