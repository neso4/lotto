package lotto.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Buyer {
	
	private List<Lotto> purchasedLotteries = new ArrayList<>();
	private final int purchaseAmount;
	private Map<Rank, Integer> lottoRankResult = new HashMap<>();
	private static final double PERCENT = 100;
	
	public Buyer(int purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	
	public void initLottoResult() {
		lottoRankResult.put(Rank.ZERO_LOSE, 0);
		lottoRankResult.put(Rank.ONE_LOSE, 0);
		lottoRankResult.put(Rank.TWO_LOSE, 0);
		lottoRankResult.put(Rank.FIFTH, 0);
		lottoRankResult.put(Rank.FOURTH, 0);
		lottoRankResult.put(Rank.THIRD, 0);
		lottoRankResult.put(Rank.SECOND, 0);
		lottoRankResult.put(Rank.FIRST, 0);
	}
	
	public void buyLotto(Lotto lotto) {
		purchasedLotteries.add(lotto);
	}
	
	public List<Lotto> getPurchasedLotteries() {
		return purchasedLotteries;
	}
	
	public int getPurchaseAmount() {
		return purchaseAmount;
	}

	
	public Map<Rank, Integer> getLottoResult() {
		return lottoRankResult;
	}
	
	public long getTotalPrize() {
		long totalPrize = 0;
		for(Rank rank : lottoRankResult.keySet()) {
			totalPrize += rank.getPrize() * lottoRankResult.get(rank);
		}
		return totalPrize;
	}
	
	public double getYield() {
		double yield = 0;
		double profitRate = (double) getTotalPrize() / purchaseAmount;
		yield = profitRate * PERCENT;
		return yield;
	}

}
