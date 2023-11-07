package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JudgeTest {
    @Test
    void 로또_번호와_당첨_번호의_일치_개수_리턴() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> numbers = Arrays.asList(4, 5, 6, 7, 8, 9);
        Lotto lotto = new Lotto(numbers);

        assertThat(Judge.winningCount(lotto, winningNumbers)).isEqualTo(3);
    }

    @Test
    void 로또_번호와_보너스_번호의_일치_여부_리턴() {
        List<Integer> numbers = Arrays.asList(4, 5, 6, 7, 8, 9);
        Lotto lotto = new Lotto(numbers);
        Integer bonusNumber = 5;

        assertThat(Judge.isBonusMatch(lotto, bonusNumber)).isTrue();
    }

    @Test
    void 해당_로또의_순위_리턴() {
        List<Integer> numbers = Arrays.asList(4, 5, 6, 7, 8, 9);
        Lotto lotto = new Lotto(numbers);

        List<Integer> winningNumbers = Arrays.asList(4, 5, 6, 7, 10, 11);
        Integer bonusNumber = 3;

        assertThat(Judge.resultPerLotto(lotto, winningNumbers, bonusNumber)).isEqualTo(Ranking.FOURTH);
    }

    @Test
    void 로또들의_결과_배열_리턴() {
        List<Integer> numbers = Arrays.asList(4, 5, 6, 7, 8, 9);
        Lotto lotto1 = new Lotto(numbers);

        numbers = Arrays.asList(4, 5, 6, 7, 10, 11);
        Lotto lotto2 = new Lotto(numbers);

        List<Lotto> lottos = Arrays.asList(lotto1, lotto2);

        List<Integer> winningNumbers = Arrays.asList(4, 5, 6, 7, 10, 11);
        Integer bonusNumber = 3;

        List<Integer> result = Arrays.asList(1, 0, 0, 1, 0);

        assertThat(Judge.result(lottos, winningNumbers, bonusNumber)).isEqualTo(result);
    }

    @Test
    void 수익률_계산() {
        List<Integer> lottoResult = Arrays.asList(1, 0, 0, 1, 0);

        Double result = 100002500d;
        Integer price = 2000;

        assertThat(Judge.rateOfReturn(lottoResult, price)).isEqualTo(result);
    }
}
