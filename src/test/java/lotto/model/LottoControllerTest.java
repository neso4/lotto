package lotto.model;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class LottoControllerTest {

    LottoUtil lottoController;

    @BeforeEach
    void set() {
        lottoController = new LottoUtil();
    }

    @Test
    void 로또_정답_비교_테스트() {
        WinningLotto winningLotto = WinningLotto.getInstance();
        winningLotto.setLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        winningLotto.setBonusNumber(7);

        List<Lotto> lottos = lottoController.lottoCreateCount(List.of(
                List.of(1, 2, 3, 4, 5, 6), // 6개 모두 일치
                List.of(1, 2, 3, 4, 5, 7), // 5개 일치 + 보너스 일치
                List.of(1, 2, 3, 4, 42, 7), // 4개 일치 + 보너스 일치
                List.of(1, 2, 3, 41, 42, 43), // 3개 일치
                List.of(41, 42, 43, 44, 45, 31) // 0개 일치
        ));

        Map<Rank, Integer> rank = lottoController.compareLottoNumbers(lottos, winningLotto);

        Assertions.assertThat(rank.get(Rank.ONE)).isEqualTo(1);
        Assertions.assertThat(rank.get(Rank.TWO)).isEqualTo(1);
        Assertions.assertThat(rank.get(Rank.FOUR)).isEqualTo(1);
        Assertions.assertThat(rank.get(Rank.NONE)).isEqualTo(1);


    }

    @Test
    void 로또_생성_테스트() {
        List<Lotto> lottos = lottoController.lottoCreateCount(List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 6, 12, 7, 35)
        ));

        Assertions.assertThat(lottos.get(0).getNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
        Assertions.assertThat(lottos.get(1).getNumbers()).isEqualTo(List.of(1, 2, 6, 7, 12, 35));

    }


}