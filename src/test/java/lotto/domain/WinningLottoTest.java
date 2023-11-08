package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class WinningLottoTest {
	@Test
	void _1등결과를_반환한다() {
		Lotto lotto = new Lotto(new ArrayList<>(List.of(1,2,3,4,5,6)));
		WinningNumbers winningNumbers = new WinningNumbers("1,2,3,4,5,6");
		Bonus bonus = new Bonus("7");
		WinningLotto winningLotto = new WinningLotto(winningNumbers, bonus);

		Map map = winningLotto.calculateResult(List.of(lotto));

		assertThat(map.containsKey(Prize.FIRST)).isEqualTo(true);
	}

	@Test
	void _2등결과를_반환하다() {
		Lotto lotto = new Lotto(new ArrayList<>(List.of(1,2,3,4,5,7)));
		WinningNumbers winningNumbers = new WinningNumbers("1,2,3,4,5,6");
		Bonus bonus = new Bonus("7");
		WinningLotto winningLotto = new WinningLotto(winningNumbers, bonus);

		Map map = winningLotto.calculateResult(List.of(lotto));

		assertThat(map.containsKey(Prize.SECOND)).isEqualTo(true);
	}

	@Test
	void 수익률을_계산한다() {
		Lotto lotto = new Lotto(new ArrayList<>(List.of(1,2,3,4,5,6)));
		WinningNumbers winningNumbers = new WinningNumbers("1,2,3,4,5,6");
		Bonus bonus = new Bonus("7");
		WinningLotto winningLotto = new WinningLotto(winningNumbers, bonus);
		winningLotto.calculateResult(List.of(lotto));

		double rate = winningLotto.getRateOfReturn(1000);
		assertThat(rate).isEqualTo((2_000_000_000 / 1000) * 100);
	}
}
