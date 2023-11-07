package lotto.dataObjects;

import java.util.List;

import lotto.enumContainer.ErrorOperation;

public class Lotto {
	private final List<Integer> numbers;

	public Lotto(List<Integer> numbers) {
		validate(numbers);
		validateDupliCate(numbers);
		validateSizeOfCollection(numbers);
		this.numbers = numbers;
	}

	private void validate(List<Integer> numbers) {
		if (numbers.size() != 6) {
			ErrorOperation.COLLECTION_SIZE_ERROR.apply();
		}
	}

	private void validateDupliCate(List<Integer> numbers) {
		boolean isDuplicate = numbers.stream()
			.distinct()
			.toList()
			.size() != 6;
		if (isDuplicate) {
			ErrorOperation.SELF_DUPLICATE_ERROR.apply();
		}
	}

	private void validateSizeOfCollection(List<Integer> numbers) {
		if (numbers.size() != 6) {
			ErrorOperation.COLLECTION_SIZE_ERROR.apply();
		}
	}

	public void validateDuplicateWithBonus(int number) {
		if (numbers.contains(number)) {
			ErrorOperation.BONUS_DUPLICATE_ERROR.apply();
		}
	}
}
