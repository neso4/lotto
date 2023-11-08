package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.constant.LottoResultConstant.INIT_REWARD;
import static lotto.constant.LottoResultConstant.NO_BENEFIT;
import static lotto.constant.LottoResultConstant.PERCENTAGE_INDICATOR;
import static lotto.constant.LottoResultConstant.ROUND_INDICATOR;

public class LottoResult {

	private static final Integer INIT_PRIZE_COUNT = 0;
	private final Map<Prize, Integer> lottoResult;

	private LottoResult(final List<Prize> lottoResult) {
		this.lottoResult = new HashMap<>();
		initLottoResult();
		updateLottoResult(lottoResult);
	}

	public static LottoResult create(final List<Prize> lottoResult) {
		return new LottoResult(lottoResult);
	}

	private void initLottoResult() {
		for (final Prize prize : Prize.values()) {
			lottoResult.put(prize, INIT_PRIZE_COUNT);
		}
	}

	private void updateLottoResult(final List<Prize> prizes) {
		for (final Prize prize : prizes) {
			Integer currentCount = lottoResult.get(prize);
			currentCount++;
			lottoResult.put(prize, currentCount);
		}
	}

	public Integer countPrize(final Prize prize) {
		return lottoResult.get(prize);
	}


	private Double getTotalReward() {
		Double totalReward = INIT_REWARD.getSetting();
		for (final Prize prize : Prize.values()) {
			Integer currentCount = lottoResult.get(prize);
			totalReward += prize.getReward() * currentCount;
		}

		return totalReward;
	}

	public Double getRoundedTotalBenefit(final Integer totalSpendAmount) {
		final Double totalBenefit = calculateTotalBenefit(totalSpendAmount);
		final Double roundedTotalBenefit = roundOffTotalBenefit(totalBenefit);
		return roundedTotalBenefit;
	}

	private Double calculateTotalBenefit(final Integer totalSpendAmount) {
		final Double totalReward = getTotalReward();
		if (totalReward == INIT_REWARD.getSetting()) {
			return NO_BENEFIT.getSetting();
		}

		return (totalReward / (double) totalSpendAmount) * PERCENTAGE_INDICATOR.getSetting();
	}


	private Double roundOffTotalBenefit(final Double totalBenefit) {
		return Math.round(totalBenefit * ROUND_INDICATOR.getSetting())
			/ ROUND_INDICATOR.getSetting();
	}

}