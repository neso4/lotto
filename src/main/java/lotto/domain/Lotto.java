package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.util.ErrorMessage;

public class Lotto {

  public static final int MIN_LOTTO_NUMBER = 1;
  public static final int MAX_LOTTO_NUMBER = 45;
  public static final int SIZE_OF_LOTTO = 6;
  private final List<Integer> lottoNumbers;

  public Lotto(List<Integer> lottoNumbers) {
    validateLottoNumbers(lottoNumbers);
    this.lottoNumbers = lottoNumbers;
  }

  public int getMatchCount(UserInputNumbers receivedLotto) {
    return (int) lottoNumbers.stream()
        .filter(number -> receivedLotto.getReceivedLottoNumbers().contains(number))
        .count();
  }

  public boolean isBonusMatch(UserInputNumbers receivedLotto) {
    return lottoNumbers.contains(receivedLotto.getBonusNumber());
  }

  private void validateLottoNumbers(List<Integer> lottoNumbers) {
    if (lottoNumbers.size() != SIZE_OF_LOTTO) {
      throw new IllegalArgumentException(ErrorMessage.NOT_SIX_NUMBERS.getMessage());
    }

    for (int number : lottoNumbers) {
      if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
        throw new IllegalArgumentException(ErrorMessage.NOT_IN_RANGE.getMessage());
      }
    }

    Set<Integer> uniqueNumbers = new HashSet<>(lottoNumbers);
    if (uniqueNumbers.size() < lottoNumbers.size()) {
      throw new IllegalArgumentException(ErrorMessage.MUST_NOT_DUPLICATE.getMessage());
    }
  }

  public List<Integer> getLottoNumbers() {
    return lottoNumbers;
  }
}
