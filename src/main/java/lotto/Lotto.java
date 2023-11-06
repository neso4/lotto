package lotto;

import java.util.List;

public class Lotto {
	private final List<Integer> numbers;

	public Lotto(List<Integer> numbers) {
		validate(numbers);
		this.numbers = numbers;
	}

	private void validate(List<Integer> numbers) throws IllegalArgumentException{
		if (numbers.size() != 6) {
			throw new IllegalArgumentException(ErrorCode.ELEMENTS_SHOULD_BE_SIX.getErrorMessage());
		}
	}

	// TODO: 추가 기능 구현
}
