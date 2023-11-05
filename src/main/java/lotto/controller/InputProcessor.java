package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputProcessor {
    private static final int LOTTO_PRICE = 1000;
  public static String getInput;

  public static int calculateLottoCount(int money) {
      return money / LOTTO_PRICE;
    }

  public static List<String> splitWinningNumbers(String input) {
    String[] numberStrings = input.split(",");
    return Arrays.asList(numberStrings);
  }

  public static List<Integer> convertToIntegerList(List<String> numberStrings) {
    return numberStrings.stream()
        .map(String::trim)
        .map(Integer::parseInt)
        .collect(Collectors.toList());
  }

}

