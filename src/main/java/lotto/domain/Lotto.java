package lotto.domain;

import static lotto.validator.LottoValidator.validateLotto;

import java.util.Collections;
import java.util.List;

public class Lotto {

	private final List<Integer> numbers;

	public Lotto(List<Integer> numbers) {
		validateLotto(numbers);
		Collections.sort(numbers);
		this.numbers = numbers;
	}

	public List<Integer> getLottoNumbers() {
		return numbers;
	}

	public int countMatch(Lotto winningLotto) {
		return (int) numbers.stream().filter(winningLotto::containNumber).count();
	}

	public boolean containNumber(int number) {
		return numbers.contains(number);
	}

}