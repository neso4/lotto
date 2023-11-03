package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.Result;
import lotto.domain.WinningStatistics;
import lotto.utils.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoServiceTest {

    private LottoWinningStrategy lottoWinningStrategy;
    private NumberGenerator randomNumberGenerator;
    private NumberGenerator fixedNumberGenerator;
    private LottoService lottoService;

    @BeforeEach
    void beforeEach() {
        lottoWinningStrategy = new DefaultLottoWinningStrategy();
        randomNumberGenerator = new RandomNumberGenerator();
        fixedNumberGenerator = new FixedNumberGenerator(fixedNumberGenerator());
    }

    @DisplayName("로또 구매 수량 검증 테스트")
    @Test
    void buyLottoTest() {
        lottoService = new LottoService(lottoWinningStrategy, randomNumberGenerator);
        int lottoQuantity = 3;

        List<Lotto> lottos = lottoService.buyLottos(lottoQuantity);

        assertThat(lottos.size()).isEqualTo(lottoQuantity);
    }

    @DisplayName("1등 당첨 검증 테스트")
    @Test
    void winnerNumberMatchTest() {
        lottoService = new LottoService(lottoWinningStrategy, fixedNumberGenerator);
        int lottoQuantity = 1;
        List<Lotto> lottos = lottoService.buyLottos(lottoQuantity);

        List<Integer> winnerNumbers = fixedNumberGenerator();
        int bonusNumber = 7;

        List<Result> results = lottos.stream()
                .map(lotto -> lotto.determineResult(lottoWinningStrategy, winnerNumbers, bonusNumber))
                .toList();

        for (Result result : results) {
            assertThat(result.getRank()).isEqualTo(Rank.FIRST);
        }
    }

    @DisplayName("2등 당첨 검증 테스트")
    @Test
    void secondRankTest() {
        lottoService = new LottoService(lottoWinningStrategy, fixedNumberGenerator);
        int lottoQuantity = 1;
        List<Lotto> lottos = lottoService.buyLottos(lottoQuantity);

        List<Integer> winnerNumbers = List.of(1, 2, 3, 4, 5, 10);
        int bonusNumber = 6;

        List<Result> results = lottos.stream()
                .map(lotto -> lotto.determineResult(lottoWinningStrategy, winnerNumbers, bonusNumber))
                .toList();

        for (Result result : results) {
            assertThat(result.getRank()).isEqualTo(Rank.SECOND);
        }

    }

    @DisplayName("로또 당첨 수익률 검증 테스트")
    @Test
    void winningStatisticsTest() {
        lottoService = new LottoService(lottoWinningStrategy, fixedNumberGenerator);
        int lottoQuantity = 1;
        List<Lotto> lottos = lottoService.buyLottos(lottoQuantity);
        List<Integer> winnerNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 10;

        WinningStatistics winningStatistics = lottoService.getWinningStatistics(lottos, winnerNumbers, bonusNumber);

        int expectProfitRate = Rank.FIRST.getPrizeMoney() / 1000 * 100;

        assertThat(winningStatistics.getProfitRate()).isEqualTo(expectProfitRate);
    }

    static List<Integer> fixedNumberGenerator() {
        return List.of(1, 2, 3, 4, 5, 6);
    }

}