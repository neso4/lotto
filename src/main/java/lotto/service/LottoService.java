package lotto.service;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import lotto.calculator.LottoCalculator;
import lotto.domain.AnswerLotto;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.generator.LottosGenerator;
import lotto.util.message.WinningStatisticsMessage;

public class LottoService {

	private final LottosGenerator lottosGenerator;
	private final LottoCalculator lottoCalculator;

	public LottoService() {
		this.lottosGenerator = new LottosGenerator();
		this.lottoCalculator = new LottoCalculator();
	}

	public Lottos createLottos(Money money) {
		lottosGenerator.setMoney(money);
		return lottosGenerator.generate();
	}

	public AnswerLotto createAnswerLotto(Lotto lotto, BonusNumber bonusNumber) {
		return new AnswerLotto(lotto, bonusNumber);
	}

	public int calculateLottoPriceSum(Lottos lottos, AnswerLotto answerLotto) {
		EnumMap<LottoRank, Integer> lottoRankCounter = countLottoRank(lottos, answerLotto);
		return lottoCalculator.calculateSum(lottoRankCounter);
	}

	public double calculateTotalReturn(int money, int lottoPriceSum) {
		return lottoCalculator.calculateRate(money, lottoPriceSum);
	}

	public String getWinningStatisticsMessage(Lottos lottos, AnswerLotto answerLotto) {
		StringJoiner strJoiner = new StringJoiner(System.lineSeparator());
		Set<Entry<LottoRank, Integer>> lottoRankCounter = countLottoRank(lottos, answerLotto).entrySet();

		for (Map.Entry<LottoRank, Integer> entry : lottoRankCounter) {
			LottoRank lottoRank = entry.getKey();
			int number = entry.getValue();
			boolean isBonusNumber = lottoRank.getisBonusNumber();

			if (lottoRank != LottoRank.NOTHING) {
				strJoiner.add(getFormattedMessage(isBonusNumber, lottoRank, number));
			}
		}
		return strJoiner.toString();
	}

	private EnumMap<LottoRank, Integer> countLottoRank(Lottos lottos, AnswerLotto answerLotto) {
		EnumMap<LottoRank, Integer> lottRankCounter = LottoRank.initializeLottoRankCounter();

		lottos.getLottos().stream()
				.map(lotto -> LottoRank.getMatchedLottoRank(answerLotto, lotto))
				.filter(Objects::nonNull)
				.forEach(lottoRank -> lottRankCounter.put(lottoRank, lottRankCounter.get(lottoRank) + 1));

		return lottRankCounter;
	}

	private String getFormattedMessage(boolean isBonusNumber, LottoRank rank, int count) {
		WinningStatisticsMessage message = filterBonusMessage(isBonusNumber);
		return message.getFormattedMessage(rank.getMatchedNumCount(), rank.getLottoPrice(), count);
	}

	private WinningStatisticsMessage filterBonusMessage(boolean isBonusNumber) {
		if (isBonusNumber) {
			return WinningStatisticsMessage.HAVE_BONUS_NUMBER;
		}

		return WinningStatisticsMessage.DONT_HAVE_BONUS_NUMBER;
	}
}
