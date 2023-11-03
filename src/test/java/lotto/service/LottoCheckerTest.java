package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import lotto.lotto.Lotto;
import lotto.lotto.LottoBuyer;
import lotto.lotto.win.WinResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoCheckerTest {

    LottoChecker lottoChecker;
    LottoBuyer lottoBuyer;
    List<Integer> target = List.of(6, 5, 4, 3, 2, 1);
    int bonus = 7;
    Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 7));
    Lotto lotto3 = new Lotto(List.of(1, 2, 3, 4, 5, 16));
    Lotto lotto4 = new Lotto(List.of(1, 2, 3, 4, 15, 16));
    Lotto lotto5 = new Lotto(List.of(1, 2, 3, 14, 15, 16));
    Lotto lotto6 = new Lotto(List.of(1, 2, 13, 14, 15, 16));
    Lotto lotto7 = new Lotto(List.of(1, 12, 13, 14, 15, 16));
    Lotto lotto8 = new Lotto(List.of(11, 12, 13, 14, 15, 16));

    @BeforeEach
    void 생성자주입() {
        List<Lotto> lottos = List.of(lotto1, lotto2, lotto3, lotto4, lotto5, lotto6, lotto7, lotto8);
        lottoBuyer = new LottoBuyer(lottos);
        lottoChecker = new LottoChecker(lottoBuyer, target, bonus);
    }

    @Test
    @DisplayName("실패 3개, 각 등수마다 1개씩")
    void 로또체커_통합_테스트() {
        Map<WinResult, Integer> map = lottoChecker.checkAllLotto();

        assertThat(map.get(WinResult.FIRST)).isEqualTo(1);
        assertThat(map.get(WinResult.SECOND)).isEqualTo(1);
        assertThat(map.get(WinResult.THIRD)).isEqualTo(1);
        assertThat(map.get(WinResult.FORTH)).isEqualTo(1);
        assertThat(map.get(WinResult.FIFTH)).isEqualTo(1);
        assertThat(map.get(WinResult.FAIL)).isEqualTo(3);
    }
}
