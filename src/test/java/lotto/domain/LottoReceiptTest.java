package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoReceiptTest {

    @Test
    @DisplayName("로또와 당첨 번호가 주어지면 올바르게 결과를 반환한다.")
    public void getReceiptResults() {
        // given
        LottoReceipt lottoReceipt = new LottoReceipt(
                List.of(
                        new Lotto(Stream.of(1, 2, 3, 4, 5, 6).map(LottoBall::new).toList()),
                        new Lotto(Stream.of(1, 2, 3, 4, 5, 6).map(LottoBall::new).toList()),
                        new Lotto(Stream.of(3, 10, 11, 12, 13, 14).map(LottoBall::new).toList()),
                        new Lotto(Stream.of(3, 10, 11, 12, 13, 14).map(LottoBall::new).toList()),
                        new Lotto(Stream.of(3, 10, 11, 13, 15, 16).map(LottoBall::new).toList())
                )
        );
        Lotto winningLotto = new Lotto(Stream.of(1, 2, 3, 4, 10, 12).map(LottoBall::new).toList());
        LottoBall bonusBall = new LottoBall(6);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusBall);

        // when
        Map<Rank, Integer> results = lottoReceipt.getResults(winningNumbers);

        // then
        assertThat(results.get(Rank.FOURTH)).isEqualTo(2);
        assertThat(results.get(Rank.FIFTH)).isEqualTo(2);
        assertThat(results.get(Rank.NONE)).isEqualTo(1);
    }

    @Test
    @DisplayName("영수증에서 로또 번호를 정확하게 가져온다.")
    public void getDrawnLottoNumbers(){
        // given
        LottoReceipt lottoReceipt = new LottoReceipt(
                List.of(
                        new Lotto(Stream.of(1, 2, 3, 4, 5, 6).map(LottoBall::new).toList())
                )
        );
        // when
        List<List<Integer>> drawnLotteryNumbers = lottoReceipt.getDrawnLotteryNumbers();
        // then
        assertThat(drawnLotteryNumbers).hasSize(1);
        assertThat(drawnLotteryNumbers.get(0)).hasSameElementsAs(List.of(1, 2, 3, 4, 5, 6));
    }
}
