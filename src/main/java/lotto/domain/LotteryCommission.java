package lotto.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lotto.type.LottoResult;
import lotto.utils.Constants;

public class LotteryCommission {
	private List<Integer> winningNumbers;
	private Integer bonusNumber;

	public LotteryCommission(List<Integer> winningNumbers) {
		this.winningNumbers = winningNumbers;
		validateWinningNumbers();
	}

	private void validateWinningNumbers() {
		if (this.winningNumbers.size() != Constants.WINNING_NUMBER_LENGTH) {
			throw new IllegalArgumentException("[ERROR] " + Constants.WINNING_NUMBER_LENGTH + "개를 입력해야 합니다!");
		}

		if (this.winningNumbers.stream().distinct().toList().size() < Constants.WINNING_NUMBER_LENGTH) {
			throw new IllegalArgumentException("[ERROR] 중복된 숫자는 입력할 수 없습니다!");
		}

		for (Integer number : this.winningNumbers) {
			if (number < Constants.LOTTO_MIN_NUMBER || number > Constants.LOTTO_MAX_NUMBER) {
				throw new IllegalArgumentException("[ERROR] 1-45 사이의 숫자만 입력할 수 없습니다!");
			}
		}
	}

	private void validateBonusNumber(Integer bonusNumber) {
		if (bonusNumber < Constants.LOTTO_MIN_NUMBER || bonusNumber > Constants.LOTTO_MAX_NUMBER) {
			throw new IllegalArgumentException("[ERROR] 1-45 사이의 숫자만 입력할 수 없습니다!");
		}

		if (winningNumbers.contains(bonusNumber)) {
			throw new IllegalArgumentException("[ERROR] 중복된 숫자는 입력할 수 없습니다!");
		}
	}

	public List<Integer> getWinningNumbers() {
		return winningNumbers;
	}

	public void setBonusNumber(Integer bonusNumber) {
		validateBonusNumber(bonusNumber);
		this.bonusNumber = bonusNumber;
	}

	public Integer getBonusNumber() {
		return bonusNumber;
	}

	public void setLottoPaperResult(LottoPaper lottoPaper) {
		Map<Integer, LottoResult> resultMapping = createResultMapping();
		Map<LottoResult, Integer> results = createResults();

		lottoPaper.getLottos().forEach(lotto -> {
			int lottoResult = getLottoResult(lotto);
			LottoResult mappedResult = resultMapping.get(lottoResult);
			if (mappedResult != null) {
				updateResult(results, mappedResult);
			}
		});

		lottoPaper.setResults(results);
	}

	private Map<Integer, LottoResult> createResultMapping() {
		Map<Integer, LottoResult> resultMapping = new HashMap<>();
		resultMapping.put(3, LottoResult.THREE);
		resultMapping.put(4, LottoResult.FOUR);
		resultMapping.put(5, LottoResult.FIVE);
		resultMapping.put(6, LottoResult.SIX);
		return resultMapping;
	}

	private void updateResult(Map<LottoResult, Integer> results, LottoResult lottoResult) {
		results.put(lottoResult, results.get(lottoResult) + 1);
	}

	public Integer getLottoResult(Lotto lotto) {
		Integer count = 0;

		for (Integer number : lotto.getNumbers()) {
			if (winningNumbers.contains(number)) {
				count++;
			}
		}
		return count;
	}

	public Boolean isBonusNumber(Lotto lotto) {
		return lotto.getNumbers().contains(bonusNumber);
	}

	public Map<LottoResult, Integer> createResults() {
		Map<LottoResult, Integer> results = new LinkedHashMap<>();
		results.put(LottoResult.THREE, 0);
		results.put(LottoResult.FOUR, 0);
		results.put(LottoResult.FIVE, 0);
		results.put(LottoResult.FIVE_BONUS, 0);
		results.put(LottoResult.SIX, 0);
		return results;
	}
}
