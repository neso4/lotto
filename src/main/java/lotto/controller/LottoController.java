package lotto.controller;

import static lotto.constant.Format.DELIMITER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lotto.model.BonusNumber;
import lotto.model.Lotto;
import lotto.model.LottoAmountofMoney;
import lotto.model.LottoChecker;
import lotto.model.RandomLottos;
import lotto.model.WinningReward;
import lotto.view.DisplayInput;
import lotto.view.DisplayOutput;

public class LottoController {

    private final DisplayOutput displayOutput = new DisplayOutput();
    private final DisplayInput displayInput = new DisplayInput();

    private final boolean EXCEPTIONLOOPCHECKER = true;

    public void play() {
        LottoAmountofMoney lottoAmountofMoney = controlLottoAmountOfMoney();
        RandomLottos randomLottos = getRandomLottos(lottoAmountofMoney);
        Lotto answerLotto = controlAnswerLotto();
        BonusNumber bonusNumber = controlBonusNumber(answerLotto);

        LottoChecker lottoChecker = new LottoChecker(randomLottos, answerLotto, bonusNumber);
        HashMap<String, Integer> winningStatics = getWinningStatics(lottoChecker);

        WinningReward winningRewardChecker = new WinningReward();
        Double winningReward = getWinningReward(winningStatics, lottoAmountofMoney.getLottoAmountofMoney(),
                winningRewardChecker);
    }

    private LottoAmountofMoney controlLottoAmountOfMoney() {
        while (EXCEPTIONLOOPCHECKER) {
            try {
                LottoAmountofMoney lottoAmountofMoney = new LottoAmountofMoney(getLottoAmountofMoney());
                return lottoAmountofMoney;
            } catch (IllegalArgumentException e) {
                displayOutput.outputExceptionMessage(e);
            }
        }
    }

    private Lotto controlAnswerLotto() {
        while (EXCEPTIONLOOPCHECKER) {
            try {
                Lotto answerLotto = new Lotto(getAnswerLottoNumber());
                return answerLotto;
            } catch (IllegalArgumentException e) {
                displayOutput.outputExceptionMessage(e);
            }
        }
    }

    private BonusNumber controlBonusNumber(Lotto answerLotto) {
        while (EXCEPTIONLOOPCHECKER) {
            try {
                BonusNumber bonusNumber = new BonusNumber(getBonusNumber(), answerLotto);
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                displayOutput.outputExceptionMessage(e);
            }
        }
    }

    private String getLottoAmountofMoney() {
        displayOutput.outputLottoAmountofMoney();
        return displayInput.inputLottoAmountofMoney();
    }

    private List<Integer> getAnswerLottoNumber() {
        displayOutput.outputAnswerLotto();
        List<Integer> lottoNumbers = new ArrayList<>();
        String inputtedLottoNumbers = displayInput.inputLottoNumbers();
        String[] inputtedlottoNumberList = inputtedLottoNumbers.split(DELIMITER.getValue());
        for (String lottoNumber : inputtedlottoNumberList) {
            lottoNumbers.add(Integer.parseInt(lottoNumber));
        }
        return lottoNumbers;
    }

    private Integer getBonusNumber() {
        displayOutput.outputBonusNumber();
        return displayInput.inputBonusNumber();
    }

    private RandomLottos getRandomLottos(LottoAmountofMoney lottoAmountofMoney) {
        RandomLottos randomLottos = new RandomLottos(lottoAmountofMoney);
        displayOutput.outputRandomLottos(randomLottos, lottoAmountofMoney);
        return randomLottos;
    }

    private HashMap<String, Integer> getWinningStatics(LottoChecker lottoChecker) {
        HashMap<String, Integer> winningStatics = lottoChecker.checkLottoNumbers();
        displayOutput.outputWinningStatics(winningStatics);
        return winningStatics;
    }

    private Double getWinningReward(HashMap<String, Integer> winningStatics, Integer lottoAmountofMoney,
                                    WinningReward winningRewardchecker) {
        Double winningReward = winningRewardchecker.calculateRateOfReturn(winningStatics, lottoAmountofMoney);
        displayOutput.outputWinningReward(winningReward);
        return winningReward;
    }


}
