package lotto.domain;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class LottosTest {
    @DisplayName("로또 한개 생성 저장")
    @Test
    void 로또_한개_생성_저장() {
        Lottos lottos = new Lottos();

        Lotto lotto = lottos.createLotto(() -> Arrays.asList(1, 2, 3, 4, 5, 6));

        assertThat(lotto).isEqualTo(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        assertThat(lottos.getLottos().size()).isEqualTo(1);
    }
    @DisplayName("로또를 생성할 때 숫자들을 오름차순 정렬해서 저장")
    @Test
    void 로또숫자들을_오름차순_정렬후_저장() {
        Lottos lottos = new Lottos();

        Lotto lotto = lottos.createLotto(() -> Arrays.asList(6, 5, 4, 3, 2, 1));

        assertThat(lotto).isEqualTo(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        assertThat(lottos.getLottos().size()).isEqualTo(1);
    }

    @DisplayName("개별 로또의 등수를 모아서 우선순위 정렬 후 반환")
    @Test
    void 각_로또의_등수를_모아_우선순위_정렬후_반환() {
        List<Integer> winnigNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(40, 41, 42, 43, 44, 45)))
        );

        assertThat(lottos.calRanksWithWinningNumbers(winnigNumbers, bonusNumber))
                .isEqualTo(new Ranks(List.of(Rank.FIRST, Rank.SECOND, Rank.LAST)));
    }
}
