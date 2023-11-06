package lotto.validator;

import java.util.List;
import lotto.view.InputView;

public class BonusNumberValidator {
  public static final int MAX_LOTTO_NUMBER = 45;
  public static final int MIN_LOTTO_NUMBER = 1;
  public static final String VALUE_OUT_OF_RANGE_MESSAGE = "[ERROR] " + MIN_LOTTO_NUMBER + "와 " + MAX_LOTTO_NUMBER + " 사이의 값을 입력하시오.";
  public static final String BONUS_NUMBER_NOT_NUMBER_MESSAGE = "[ERROR] 숫자가 아닙니다.";
  public static final String EMPTY_BONUS_NUMBER_MESSAGE = "[ERROR] 공백을 입력하였습니다.";
  public static final String DUPLICATE_LOTTO_NUMBERS_MESSAGE = "[ERROR] 중복된 번호가 있습니다.";

  public static void isValueInRange(String input) {
    if(Integer.parseInt(input) < MIN_LOTTO_NUMBER || Integer.parseInt(input) > MAX_LOTTO_NUMBER ){
      throw new IllegalArgumentException(VALUE_OUT_OF_RANGE_MESSAGE);
    }
  }

  public static void isAllIntegersValid(String input) {
    if (!input.matches("^[0-9\\s]+$")) {
      throw new IllegalArgumentException(BONUS_NUMBER_NOT_NUMBER_MESSAGE);
    }
  }

  public static void isEmptyString(String input) {
    if(input.isEmpty()) {
      throw new IllegalArgumentException(EMPTY_BONUS_NUMBER_MESSAGE);
    }
  }

  public static void isDuplicate(List<Integer> winningNumberSet ,int input) {
      Integer inputInteger = input;
      if (winningNumberSet.contains(inputInteger)) {
        throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBERS_MESSAGE);
      }
  }
}
