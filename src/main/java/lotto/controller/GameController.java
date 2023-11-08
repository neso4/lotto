package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameController {

    private static GameController gameController;

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;

    private GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        lottoGenerator = new LottoGenerator();
    }

    public static GameController getInstance(InputView inputView, OutputView outputView) {
        if (gameController == null) {
            gameController = new GameController(inputView, outputView);
        }
        return gameController;
    }

    public void startGame() {
        outputView.notifyInputAmount();

        int amount = inputView.readAmount();

        ArrayList<Lotto> lottoBundle = lottoGenerator.generateLottoBundle(amount);
        outputView.printLottoCount(lottoBundle.size());
        outputView.printLottoNumbers(lottoBundle);

        outputView.notifyInputWinningNumbers();
        List<Integer> mainNumbers = inputView.readMainNumbers();
        outputView.notifyInputBonusNumber();
        Integer bonusNumber = inputView.readBonusNumber();


        WinningManager winningManager = new WinningManager(mainNumbers, bonusNumber);
        WinningStats winningStats = winningManager.matchAll(lottoBundle);
        outputView.printWinningStats(winningStats);
        outputView.printEarningRate(winningStats, amount);
    }
}
