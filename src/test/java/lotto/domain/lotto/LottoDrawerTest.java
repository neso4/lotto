package lotto.domain.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

final class LottoDrawerTest {
    @DisplayName("수익률 계산 테스트")
    @Test
    void calculateReturnOfRateTest() {
        // given
        final List<Integer> numbers1 = List.of(1, 2, 3, 4, 5, 6);
        final List<Integer> numbers2 = List.of(1, 2, 3, 4, 5, 7);
        final List<Integer> numbers3 = List.of(1, 2, 3, 4, 5, 10);
        final List<Integer> numbers4 = List.of(1, 2, 3, 4, 10, 11);
        final List<Integer> numbers5 = List.of(1, 2, 3, 10, 11, 12);
        final List<Integer> numbers6 = List.of(1, 2, 10, 11, 12, 13);
        final List<Integer> numbers7 = List.of(1, 10, 11, 12, 13, 14);
        final List<Integer> numbers8 = List.of(10, 11, 12, 13, 14, 15);
        final int bonusNumber = 7;

        final Lottos lottos1 = new Lottos(List.of(new Lotto(numbers1)));
        final Lottos lottos2 = new Lottos(List.of(new Lotto(numbers2)));
        final Lottos lottos3 = new Lottos(List.of(new Lotto(numbers3)));
        final Lottos lottos4 = new Lottos(List.of(new Lotto(numbers4)));
        final Lottos lottos5 = new Lottos(List.of(new Lotto(numbers5)));
        final Lottos lottos6 = new Lottos(List.of(new Lotto(numbers6)));
        final Lottos lottos7 = new Lottos(List.of(new Lotto(numbers7)));
        final Lottos lottos8 = new Lottos(List.of(new Lotto(numbers8)));

        final WinningLotto winningLotto = new WinningLotto(numbers1, bonusNumber);
        final LottoDrawer lottoDrawer = new LottoDrawer(winningLotto);

        // when
        final DrawLottosData data1 = lottoDrawer.draw(lottos1);
        final DrawLottosData data2 = lottoDrawer.draw(lottos2);
        final DrawLottosData data3 = lottoDrawer.draw(lottos3);
        final DrawLottosData data4 = lottoDrawer.draw(lottos4);
        final DrawLottosData data5 = lottoDrawer.draw(lottos5);
        final DrawLottosData data6 = lottoDrawer.draw(lottos6);
        final DrawLottosData data7 = lottoDrawer.draw(lottos7);
        final DrawLottosData data8 = lottoDrawer.draw(lottos8);

        // then
        assertThat(data1.calculateReturnOfRate()).isEqualTo(200_000_000.0);
        assertThat(data2.calculateReturnOfRate()).isEqualTo(3_000_000.0);
        assertThat(data3.calculateReturnOfRate()).isEqualTo(150_000.0);
        assertThat(data4.calculateReturnOfRate()).isEqualTo(5_000.0);
        assertThat(data5.calculateReturnOfRate()).isEqualTo(500.0);
        assertThat(data6.calculateReturnOfRate()).isEqualTo(0.0);
        assertThat(data7.calculateReturnOfRate()).isEqualTo(0.0);
        assertThat(data8.calculateReturnOfRate()).isEqualTo(0.0);
    }

}