package lotto.service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import lotto.calculator.LottoCalculator;
import lotto.domain.AnswerLotto;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.generator.LottosGenerator;
import lotto.util.message.WinningStatisticsMessage;

public class LottoService {

	private static final LottosGenerator LOTTOS_GENERATOR = new LottosGenerator();
	private static final LottoCalculator LOTTO_CALCULATOR = new LottoCalculator();
	private static final String NEW_LINE = System.lineSeparator();

	public Lottos createLottos(Money money) {
		LOTTOS_GENERATOR.setMoney(money);
		return new Lottos(LOTTOS_GENERATOR.generate());
	}

	public AnswerLotto createAnswerLotto(List<Integer> numbers, int bonusNumber) {
		return new AnswerLotto(numbers, bonusNumber);
	}

	public int calculateLottoPriceSum(Lottos lottos, AnswerLotto answerLotto) {
		EnumMap<LottoRank, Integer> lottoRankCounter = countLottoRank(lottos, answerLotto);
		return LOTTO_CALCULATOR.calculateSum(lottoRankCounter);
	}

	public double calculateTotalReturn(int money, int priceSum) {
		return LOTTO_CALCULATOR.calculateTotalReturn(money, priceSum);
	}

	public String getWinningStatisticsMessage(Lottos lottos, AnswerLotto answerLotto) {
		StringJoiner strJoiner = new StringJoiner(NEW_LINE);
		Set<Entry<LottoRank, Integer>> lottoRankCounter = countLottoRank(lottos, answerLotto).entrySet();

		for (Map.Entry<LottoRank, Integer> entry : lottoRankCounter) {
			LottoRank key = entry.getKey();
			int value = entry.getValue();
			boolean isBonusNumber = key.getisBonusNumber();

			if (key != LottoRank.NOTHING) {
				strJoiner.add(getFormattedMessage(isBonusNumber, key, value));
			}
		}
		return strJoiner.toString();
	}

	private EnumMap<LottoRank, Integer> countLottoRank(Lottos lottos, AnswerLotto answerLotto) {
		EnumMap<LottoRank, Integer> lottRankCounter = LottoRank.initializeLottoRankCounter();

		lottos.getLottos().stream().map(lotto -> LottoRank.getMatchedLottoRank(answerLotto, lotto))
				.filter(Objects::nonNull)
				.forEach(lottoRank -> lottRankCounter.put(lottoRank, lottRankCounter.get(lottoRank) + 1));

		return lottRankCounter;
	}

	private String getFormattedMessage(boolean isBonusNumber, LottoRank rank, int count) {
		WinningStatisticsMessage message = filterMessage(isBonusNumber);
		return message.getForMatMessage(rank.getMatchedNumCount(), rank.getPrice(), count);
	}

	private WinningStatisticsMessage filterMessage(boolean isBonusNumber) {
		if (isBonusNumber) {
			return WinningStatisticsMessage.HAVE_BONUS_NUMBER;
		}

		return WinningStatisticsMessage.DONT_HAVE_BONUS_NUMBER;
	}
}
