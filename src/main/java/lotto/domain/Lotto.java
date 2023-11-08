package lotto.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static lotto.constant.LottoConstant.LOTTO_LENGTH;
import static lotto.constant.LottoConstant.RANGE_START_NUMBER;
import static lotto.constant.LottoConstant.RANGE_END_NUMBER;
import static lotto.exception.LottoExceptionMessage.WRONG_LOTTO_LENGTH;
import static lotto.exception.LottoExceptionMessage.DUPLICATED_NUMBER;
import static lotto.exception.LottoExceptionMessage.WRONG_LOTTO_NUMBER_RANGE;
import static lotto.util.CharacterUnits.COMMA;
import static lotto.util.CharacterUnits.LEFT_BRACKET;
import static lotto.util.CharacterUnits.RIGHT_BRACKET;
import static lotto.util.CharacterUnits.SPACE;

public class Lotto {

	private final List<Integer> numbers;

	private Lotto(final List<Integer> numbers) {
		validateLength(numbers);
		validateEachNumberRange(numbers);
		validateDuplicatedNumber(numbers);
		this.numbers = numbers;
	}

	public static Lotto create(final List<Integer> numbers) {
		return new Lotto(numbers);
	}

	private void validateLength(final List<Integer> numbers) {
		if (numbers.size() != LOTTO_LENGTH.getSetting()) {
			throw new IllegalArgumentException(WRONG_LOTTO_LENGTH.getMessage());
		}
	}

	private void validateEachNumberRange(final List<Integer> numbers) {
		if (numbers.stream()
			.anyMatch(number ->
				!(RANGE_START_NUMBER.getSetting() <= number
					&& number <= RANGE_END_NUMBER.getSetting())
			)
		) {
			throw new IllegalArgumentException(WRONG_LOTTO_NUMBER_RANGE.getMessage());
		}
	}

	private void validateDuplicatedNumber(final List<Integer> numbers) {
		if (numbers.stream()
			.collect(
				Collectors.toSet()
			)
			.size()
			!= LOTTO_LENGTH.getSetting()) {
			throw new IllegalArgumentException(DUPLICATED_NUMBER.getMessage());
		}
	}


	public Prize compareWithWinnerLotto(final List<Integer> winnerNumbers,
		final Integer bonusNumber) {
		final Integer countOfMatchedNumber = compareWithWinnerNumbers(winnerNumbers);
		final Boolean isBonusNumber = compareWithBonusNumber(bonusNumber);
		return Prize.of(countOfMatchedNumber, isBonusNumber);
	}


	private Integer compareWithWinnerNumbers(final List<Integer> winnerNumbers) {
		return Math.toIntExact(numbers.stream()
			.filter(
				number -> winnerNumbers.contains(number)
			)
			.count());
	}

	private Boolean compareWithBonusNumber(final Integer bonusNumber) {
		return numbers.contains(bonusNumber);
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null || getClass() != that.getClass()) {
			return false;
		}
		Lotto lotto = (Lotto) that;
		return Objects.equals(numbers, lotto.numbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numbers);
	}

	@Override
	public String toString() {
		String joinedNumbers = numbers.stream()
			.map(number -> Integer.toString(number))
			.collect(Collectors.joining(COMMA.getUnit() + SPACE.getUnit()));
		return LEFT_BRACKET.getUnit() + joinedNumbers + RIGHT_BRACKET.getUnit();

	}

}
