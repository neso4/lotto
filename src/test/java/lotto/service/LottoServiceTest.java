package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.Lotto;
import lotto.dto.WinningStatisticsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottoServiceTest {
    LottoService lottoService;

    @BeforeEach
    void setup() {
        lottoService = new LottoServiceImpl(new TestNumberGenerator());
    }


    @Test
    public void 로또번호_랜덤하게_생성() throws Exception {
        //given
        int price = 1000;
        //when
        List<Lotto> lottos = lottoService.issueNewLotto(price);
        List<Integer> numbers = lottos.get(0).getNumbers();
        //then
        assertThat(numbers.size()).isEqualTo(numbers.stream()
                .distinct()
                .count());
    }

    @Test
    public void 가격이_주어지면_로또_구입() {
        //given
        int price = 2000;
        //when
        List<Lotto> lottos = lottoService.issueNewLotto(price);
        //then
        assertThat(lottos.size()).isEqualTo(2);
    }

    @Test
    public void 구입한_로또_승률_구하기_3개() {
        //given
        int price = 1000;
        int bonus = 11;
        List<Lotto> lottos = lottoService.issueNewLotto(price);
        Lotto winningLotto = new Lotto(List.of(1, 3, 5, 30, 40, 25));
        //when
        WinningStatisticsDto winningStatistics = lottoService.getWinningStatistics(lottos, winningLotto, bonus);
        //then
        assertThat(winningStatistics.threeMatches()).isEqualTo(1);
        assertThat(winningStatistics.earningRate()).isEqualTo(500);
    }

    @Test
    public void 구입한_로또_승률_구하기_보너스_포함() {
        //given
        int price = 1000;
        int bonus = 11;
        List<Lotto> lottos = lottoService.issueNewLotto(price);
        Lotto winningLotto = new Lotto(List.of(1, 3, 5, 7, 9, 10));
        //when
        WinningStatisticsDto winningStatistics = lottoService.getWinningStatistics(lottos, winningLotto, bonus);
        //then
        assertThat(winningStatistics.fiveMatchesWithBonus()).isEqualTo(1);
        assertThat(winningStatistics.fourMatches()).isEqualTo(0);
        assertThat(winningStatistics.fiveMatches()).isEqualTo(0);
        assertThat(winningStatistics.earningRate()).isEqualTo(3_000_000);
    }

    @Test
    public void 구입한_로또_승률_구하기_보너스_미포함() {
        //given
        int price = 1000;
        int bonus = 20;
        List<Lotto> lottos = lottoService.issueNewLotto(price);
        Lotto winningLotto = new Lotto(List.of(1, 3, 5, 7, 9, 10));
        //when
        WinningStatisticsDto winningStatistics = lottoService.getWinningStatistics(lottos, winningLotto, bonus);
        //then
        assertThat(winningStatistics.fiveMatchesWithBonus()).isEqualTo(0);
        assertThat(winningStatistics.fourMatches()).isEqualTo(0);
        assertThat(winningStatistics.fiveMatches()).isEqualTo(1);
        assertThat(winningStatistics.earningRate()).isEqualTo(150000);
    }


}
