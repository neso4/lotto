package lotto.domain;

import static lotto.domain.LottoNumber.*;
import static lotto.domain.LottoPrize.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
	private final List<Integer> numbers;

	public Lotto(List<Integer> numbers) {
		validate(numbers);
		this.numbers = numbers;
	}

	private void validate(List<Integer> numbers) {
		checkLottoNumbersConsistOfNumberOfLottoNumbers(numbers);
		checkLottoNumbersHasDuplicatedNumber(numbers);
		checkLottoNumbersHasNumberOutOfRange(numbers);
	}

	private void checkLottoNumbersHasNumberOutOfRange(List<Integer> numbers) {
		for (int number : numbers) {
			if (number < MIN_LOTTO_NUMBER.getValue() || number > MAX_LOTTO_NUMBER.getValue()) {
				throw new IllegalArgumentException(
					"[ERROR] 로또 번호는 " + MIN_LOTTO_NUMBER.getValue() + " 이상 " + MAX_LOTTO_NUMBER.getValue()
						+ " 이하의 값이여야 합니다.");
			}
		}
	}

	private void checkLottoNumbersHasDuplicatedNumber(List<Integer> numbers) {
		Set<Integer> lottoNumbers = new HashSet<>();
		for (int number : numbers) {
			if (lottoNumbers.contains(number)) {
				throw new IllegalArgumentException("[ERROR] 로또 번호 값이 중복되었습니다.");
			}
			lottoNumbers.add(number);
		}
	}

	private void checkLottoNumbersConsistOfNumberOfLottoNumbers(List<Integer> numbers) {
		if (numbers.size() != NUMBER_OF_LOTTO_NUMBERS.getValue()) {
			throw new IllegalArgumentException("[ERROR] 로또 번호는 " + NUMBER_OF_LOTTO_NUMBERS.getValue() + "개여야 합니다.");
		}
	}

	void printNumbers() {
		System.out.println(numbers);
	}

	LottoPrize confirmWinning(Set<Integer> winningNumbers, int bonusNumber) {
		int hits = getHits(winningNumbers);
		boolean bonusHit = isBonusHit(bonusNumber);

		for (LottoPrize lottoPrize : LottoPrize.values()) {
			if (hits == lottoPrize.getMatches()) {
				if (hits == FIVE_MATCH.getMatches() && bonusHit) {
					return FIVE_MATCH_WITH_BONUS;
				}
				return lottoPrize;
			}
		}
		return null;
	}

	private boolean isBonusHit(int bonusNumber) {
		for (int number : numbers) {
			if (number == bonusNumber) {
				return true;
			}
		}
		return false;
	}

	private int getHits(Set<Integer> winningNumbers) {
		int hits = 0;

		for (int number : numbers) {
			if (winningNumbers.contains(number)) {
				hits += 1;
			}
		}
		return hits;
	}
}
