package lotto.controller;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.List;
import java.util.Map;
import java.util.Set;
import lotto.model.Answer;
import lotto.model.Lotto;
import lotto.model.LottoGame;
import lotto.model.Prize;
import lotto.model.Rank;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    LottoGame lottoGame = new LottoGame();
    Answer answerNumber;
    Prize prize = new Prize();
    int money = 0;

    public void run() {
        beforeLottoGame();
        startLottoGame();
        showGameResult();
    }

    private void beforeLottoGame() {
        purchasePhase();
        answerInputPhase();
    }

    private void purchasePhase() {
        money = inputView.moneyInput();
        int lottoNum = inputView.calculateLottoNum(money);
        lottoGame.createLotto(lottoNum);

        List<Lotto> lottoGames = lottoGame.getLottoGames();
        outputView.showLottoNum(lottoNum, lottoGames);
    }

    private void answerInputPhase() {
        Lotto answer = inputView.answerInput();

        List<Integer> answers = answer.getLottoNumber();
        int bonus = inputView.bonusInput(answers);
        answerNumber = new Answer(answers, bonus);
    }

    private void startLottoGame() {
        prize.initPrize();
        prize.compareAnswerAndLotto(answerNumber, lottoGame);
        prize.calculateTotalPrizeMoney();
        prize.calculateProfit(money);
    }

    private void showGameResult() {
        Map<Rank, Integer> prizeResult = prize.getPrizeResult();
        Set<Rank> keySet = prizeResult.keySet();
        double profit = prize.getProfit();

        outputView.resultStatistics();
        showProfitResult(prizeResult, keySet, profit);
    }

    private void showProfitResult(Map<Rank, Integer> prizeResult, Set<Rank> keySet, double profit) {
        for (Rank key : keySet) {
            String lottoPrize = key.getConvertPrize();
            int count = key.getCount();
            int lottoNum = prizeResult.get(key);

            if (key == Rank.SECOND && count > 0) {
                outputView.showBonusResult(count, lottoPrize, lottoNum);
            }
            if (count > 2) {
                outputView.showLottoGameResult(count, lottoPrize, lottoNum);
            }
        }
        outputView.showProfitResult(profit);
    }
}
