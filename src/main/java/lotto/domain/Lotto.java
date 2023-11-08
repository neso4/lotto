package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lotto.exception.DuplicateLottoNumberException;
import lotto.exception.InvalidRangeLottoNumberException;
import lotto.exception.InvalidSizeLottoNumberException;

public class Lotto {
  private static final int LOTTO_SIZE = 6;
  private static final int MAX_RANGE = 45;
  private static final int MIN_RANGE = 1;
  private final List<Integer> numbers;

  public Lotto(List<Integer> numbers) {
    validate(numbers);
    numbers = sorted(numbers);
    this.numbers = numbers;
  }

  private void validate(List<Integer> numbers) {
    validateSize(numbers);
    validateRange(numbers);
    validateDuplicate(numbers);
  }

  private void validateSize(List<Integer> numbers) {
    if (numbers.size() != LOTTO_SIZE) {
      throw new InvalidSizeLottoNumberException();
    }
  }

  private void validateRange(List<Integer> numbers) {
    numbers.forEach(this::validateRange);
  }

  private void validateRange(Integer number) {
    if (!(MIN_RANGE <= number && number <= MAX_RANGE)) {
      throw new InvalidRangeLottoNumberException();
    }
  }

  private void validateDuplicate(List<Integer> numbers) {
    Set<Integer> validatedNumbers = new HashSet<>(numbers);
    if (validatedNumbers.size() != LOTTO_SIZE) {
      throw new DuplicateLottoNumberException();
    }
  }

  public List<Integer> sorted(List<Integer> numbers) {
    return numbers.stream()
            .sorted()
            .collect(Collectors.toList());
  }

  public boolean isContain(int number) {
    return numbers.contains(number);
  }

  public int getMatchLottoNumber(WinningLotto winningLotto) {
    return (int) numbers.stream()
            .filter(winningLotto::isContain)
            .count();
  }

  @Override
  public String toString() {
    return numbers.toString();
  }
}