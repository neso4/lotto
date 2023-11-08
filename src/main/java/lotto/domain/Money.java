package lotto.domain;

import lotto.exception.EnterLottoMoneyFormatException;
import lotto.exception.EnterLottoMoneyZeroException;

public class Money {
  private static final int ZERO = 0;
  private static final int THOUSAND = 1000;
  private final int money;

  public Money(int money) {
    validate(money);
    this.money = money;
  }

  private void validate(int inputValue) {
    validateZero(inputValue);
    validateDivideMoney(inputValue);
  }

  private void validateZero(int inputValue) {
    if (inputValue == ZERO) {
      throw new EnterLottoMoneyZeroException();
    }
  }

  private void validateDivideMoney(int inputValue) {
    if (inputValue % THOUSAND != ZERO) {
      throw new EnterLottoMoneyFormatException();
    }
  }

  public int getTicket() {
    return money / 1000;
  }
}