package lotto.domain;

import static lotto.domain.LottoGame.*;
import static lotto.domain.LottoPrize.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

class LottoResultCalculator {
	private static final String MONEY_DELIMITER = ",";
	private static final int PERCENTAGE_MULTIPLIER = 100;

	private static String integerToWon(int prize) {
		String origin = String.valueOf(prize);
		int digit = origin.length();
		StringBuilder won = new StringBuilder();
		won.append(origin.charAt(0));
		for (int i = digit - 1; i > 0; i--) {
			if (i % 3 == 0) {
				won.append(MONEY_DELIMITER);
			}
			won.append(origin.charAt(digit - i));
		}
		won.append("원");
		return won.toString();
	}

	static void printWinningDetails(List<Lotto> lottos, List<Integer> winningNumber, int bonusNumber) {
		List<LottoPrize> lottoPrizeCases = List.of(LottoPrize.values());
		Map<LottoPrize, Integer> lottoPrizeCount = new HashMap<>();
		long totalPrize = 0L;

		for (Lotto lotto : lottos) {
			LottoPrize lottoPrize = lotto.confirmWinning(new HashSet<>(winningNumber), bonusNumber);
			lottoPrizeCount.put(lottoPrize, lottoPrizeCount.getOrDefault(lottoPrize, 0) + 1);
		}

		System.out.println("당첨 통계");
		System.out.println("---");
		totalPrize = getTotalPrize(lottoPrizeCases, totalPrize, lottoPrizeCount);
		System.out.printf("총 수익률은 %.1f%%입니다.\n",
			(double)totalPrize / (lottos.size() * LOTTO_PRICE) * PERCENTAGE_MULTIPLIER);
	}

	private static long getTotalPrize(List<LottoPrize> lottoPrizeCases, long totalPrize,
		Map<LottoPrize, Integer> lottoPrizeCount) {
		for (LottoPrize lottoPrizeCase : lottoPrizeCases) {
			totalPrize += (long)lottoPrizeCase.getPrize() * lottoPrizeCount.getOrDefault(lottoPrizeCase, 0);
			if (lottoPrizeCase == FIVE_MATCH_WITH_BONUS) {
				System.out.printf("%d개 일치, 보너스 볼 일치 (%s) - %d개\n", lottoPrizeCase.getMatches(),
					integerToWon(lottoPrizeCase.getPrize()), lottoPrizeCount.getOrDefault(lottoPrizeCase, 0));
				continue;
			}
			System.out.printf("%d개 일치 (%s) - %d개\n", lottoPrizeCase.getMatches(),
				integerToWon(lottoPrizeCase.getPrize()), lottoPrizeCount.getOrDefault(lottoPrizeCase, 0));
		}
		return totalPrize;
	}
}
