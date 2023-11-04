package lotto.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.Lotto;
import lotto.model.Calculator;
import lotto.model.Rank;
import lotto.validator.BonusNumberValidator;
import lotto.validator.PurchaseAmountValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private static final int LOTTO_PRICE = 1000;
    private Lotto winnerLotto;
    private List<Lotto> userLottos;
    private Map<Rank, Integer> result;
    private Calculator calculator;
    private int purchaseAmount;
    private int bonusNumber;

    public LottoController() {
        this.userLottos = new ArrayList<>();
        this.result = new HashMap<>();
    }

    public void run() {
        initPurchaseAmount();
        generateUserLottos();
        initWinnerNumber();
        initBonusNumber();
        generateResult();
        displayResult();
    }

    private void initPurchaseAmount() {
        while (true) {
            try {
                purchaseAmount = InputView.readPurchaseAmount();
                PurchaseAmountValidator.validate(purchaseAmount);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void generateUserLottos() {
        for (int i = 0; i < purchaseAmount / LOTTO_PRICE; i++) {
            userLottos.add(Lotto.generateUserLotto());
        }
        OutputView.displayUserLottos(userLottos);
    }

    private void initWinnerNumber() {
        while (true) {
            try {
                List<Integer> numbers = InputView.readLottoNumbers();
                winnerLotto = new Lotto(numbers);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void initBonusNumber() {
        while (true) {
            try {
                bonusNumber = InputView.readBonusNumber();
                BonusNumberValidator.validate(winnerLotto, bonusNumber);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void generateResult() {
        calculator = new Calculator(winnerLotto, userLottos, bonusNumber);
        result = calculator.getCalculateResult();
    }

    private void displayResult() {
        float rateOfReturn = calculator.calculateRateOfReturn(result);
        OutputView.displayResult(result, rateOfReturn);
    }

}
