package lotto.domain;

import java.util.Collections;
import java.util.List;

import lotto.type.LottoResult;

public class Lotto {
	private final List<Integer> numbers;
	private LottoResult result = LottoResult.CLOSE;

	public Lotto(List<Integer> numbers) {
		validate(numbers);
		this.numbers = numbers;
	}

	private void validate(List<Integer> numbers) {
		if (numbers.size() != 6) {
			throw new IllegalArgumentException();
		}

		for (Integer number : numbers) {
			if (Collections.frequency(numbers, number) >= 2) {
				throw new IllegalArgumentException();
			}
		}
	}

	public String toString() {
		return numbers.toString();
	}

	public void setResult(List<Integer> winningNumbers, Integer bonus) {
		int count = 0;

		for (Integer number : numbers) {
			if (winningNumbers.contains(number)) {
				count++;
			}
		}

		if (count == 6) {
			result = LottoResult.SIX;
		}

		if (count == 5) {
			if (numbers.contains(bonus)) {
				result = LottoResult.FIVE_BONUS;
				return;
			}
			result = LottoResult.FIVE;
		}

		if (count == 4) {
			result = LottoResult.FOUR;
			return;
		}

		if (count == 3) {
			result = LottoResult.THREE;
			return;
		}
	}

	public LottoResult getResult() {
		return this.result;
	}
}
