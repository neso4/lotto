package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.model.LottoGame;
import lotto.model.LottoResult;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

  private static lotto.view.InputView inputView;
  private static lotto.controller.InputProcessor InputPresent;
  private static lotto.model.LottoGame LottoGame;

  public LottoController() {
    inputView = new InputView();
    InputPresent = new InputProcessor();
    LottoGame = new LottoGame();
  }

  public static List<List<Integer>> createLottos(int calculateLottoCount) {
    List<List<Integer>> lottos = new ArrayList<>();
    for (int i = 0; i < calculateLottoCount; i++) {
      List<Integer> lotto = LottoGame.createRandomLottoNumber();
      lottos.add(lotto);
    }
    return lottos;
  }

  public static void run() {
    int money = inputView.inputMoney();
    int calculateLottoCount = InputPresent.calculateLottoCount(money);
    OutputView.printLottoCountMessage(calculateLottoCount);
    List<List<Integer>> lottos = createLottos(calculateLottoCount);
    OutputView.printLottos(lottos);
    String winningNumber = inputView.inputWinningNumber();
    List<String> winningNumbers = InputProcessor.splitWinningNumbers(winningNumber);
    List<Integer> winningNumberSet = InputProcessor.convertToIntegerList(winningNumbers);
    int bonusNumber = inputView.inputBonusNumber(winningNumberSet);
    List<Integer> rank = checkLottoResults(lottos, winningNumberSet, bonusNumber);
    printLottoResults(money, rank);
  }

  public static List<Integer> checkLottoResults(List<List<Integer>> lottos, List<Integer> winningNumberSet, int bonusNumber) {
    List<Integer> rank = LottoGame.checkWinningStatus(lottos, winningNumberSet, bonusNumber);
    return rank;
  }

  public static void printLottoResults(int money, List<Integer> rank) {
    OutputView.printLottoCountMessage(rank.size());
    OutputView.printRanking(rank);

    int prize = LottoResult.lottoPrizeCalculator(rank);
    double profitability = LottoResult.profitabilityCalculator(money, prize);
    String formattedValue = LottoResult.calculateRoundedProfitabilityWithCommas(profitability);
    OutputView.printProfitability(formattedValue);
  }
}
