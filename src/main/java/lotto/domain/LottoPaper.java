package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.type.LottoResult;
import lotto.utils.Constants;

public class LottoPaper {
	private List<Lotto> lottos;
	private Map<LottoResult, Integer> results;

	public LottoPaper(Integer money) {
		this.lottos = new ArrayList<>();
		Integer lottoCount = money / 1000;
		System.out.println(lottoCount + "개를 구매했습니다.");
		for (int i = 0; i < lottoCount; i++) {
			Lotto lotto = new Lotto(Randoms.pickUniqueNumbersInRange(Constants.LOTTO_MIN_NUMBER, Constants.LOTTO_MAX_NUMBER, Constants.WINNING_NUMBER_LENGTH));
			System.out.println(lotto);
			this.lottos.add(lotto);
		}
	}

	public List<Lotto> getLottos() {
		return lottos;
	}

	public void setResults(Map<LottoResult, Integer> results) {
		this.results = results;
	}

	public Map<LottoResult, Integer> getResults() {
		return results;
	}
}
