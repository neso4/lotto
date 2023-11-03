package lotto.view;

import java.util.List;
import lotto.controller.InputProcessor;

public class OutputView {
  private final static String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
  private final static String INPUT_WINNINGNUMBER_MESSAGE = "당첨 번호를 입력해 주세요.";
  private final static String INPUT_BOUNSNUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";
  private final static String LOTTO_COUNT_MESSAGE = "개를 구매했습니다.";
  private lotto.controller.InputProcessor InputProcessor;

  public static void printInputMoneyMessage() {
    System.out.println(INPUT_MONEY_MESSAGE);
  }

  public static void printInputWinningNumberMessage() {
    System.out.println(INPUT_WINNINGNUMBER_MESSAGE);
  }

  public static void printInputBonusNumberMessage() {
    System.out.println(INPUT_BOUNSNUMBER_MESSAGE);
  }

  public void printLottoCountMessage() {
    int LottoCount = InputProcessor.calculateLottoCount(Integer.parseInt(InputProcessor.getInput));
    System.out.print(LottoCount);
    System.out.println(LOTTO_COUNT_MESSAGE);
  }

  public String printLottoNumbers(List<Integer> lotto) {
    StringBuilder formattedLotto = new StringBuilder("[");

    for (int i = 0; i < lotto.size(); i++) {
      formattedLotto.append(lotto.get(i));
      if (i < lotto.size() - 1) {
        formattedLotto.append(", ");
      }
    }

    formattedLotto.append("]");
    return formattedLotto.toString();
  }

  public void printLottos(List<List<Integer>> lottos) {
    for (List<Integer> lotto : lottos) {
      String formattedLotto = printLottoNumbers(lotto);
      System.out.println(formattedLotto);
    }
  }


}
