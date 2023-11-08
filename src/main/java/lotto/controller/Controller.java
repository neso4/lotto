package lotto.controller;

import static lotto.constant.Constant.INT_NULL;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoCompany;
import lotto.domain.User;
import lotto.util.NumberValidate;
import lotto.util.StringUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {
    private boolean flag;
    private String input;
    private List<Lotto> userLotto;
    private final User user;
    private final LottoCompany lottoCompany;

    public Controller(User user, LottoCompany lottoCompany) {
        this.user = user;
        this.lottoCompany = lottoCompany;
    }

    public void run() {
        inputMoney();
        printLotto();
        inputPrizeNumbers();
        inputBonusNumber();
        matchLottoByCompany();
        printRateOfReturn();
    }

    private void inputMoney() {
        flag = false;
        while (!flag) {
            tryInputMoney();
        }
    }

    private void tryInputMoney() {
        try {
            input = InputView.inputMoney();
            int money = changeStringToInt(input);
            user.purchaseLotto(money);
            flag = true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void inputPrizeNumbers() {
        flag = false;
        while (!flag) {
            tryInputPrizeNumbers();
        }
    }

    private void tryInputPrizeNumbers() {
        try {
            input = InputView.inputPrizeNumber();
            setPrizeNumbers(input);
            flag = true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setPrizeNumbers(String input) {
        List<String> splitString = StringUtil.splitWithComma(input);
        List<Integer> prizeNumbers = new ArrayList<Integer>();
        for (String string : splitString) {
            prizeNumbers.add(changeStringToInt(string));
        }
        lottoCompany.setPrizeNumbers(prizeNumbers);
    }

    private void inputBonusNumber() {
        flag = false;
        while (!flag) {
            tryInputBonusNumber();
        }
    }

    private void tryInputBonusNumber() {
        try {
            input = InputView.inputBonusNumber();
            setBonusNumber(input);
            flag = true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setBonusNumber(String input) {
        int bonusNumber = changeStringToInt(input);
        lottoCompany.setBonusNumber(bonusNumber);
    }

    private void printLotto() {
        userLotto = user.getMyLotto();
        OutputView.printPurchase(userLotto.size());
        OutputView.printLotto(userLotto);
    }

    private void matchLottoByCompany() {
        for (Lotto lotto : userLotto) {
            int rank = lottoCompany.matchPrize(lotto.getNumbers());
            receivePrize(rank);
        }
    }

    private void receivePrize(int rank) {
        if (rank != INT_NULL) {
            user.winningTheLotto(rank);
        }
    }

    private void printRateOfReturn() {
        OutputView.printPrizeStatistics(user.getPrizeCount());
        OutputView.printRateOfReturn(user.getRateOfReturn());
    }

    private int changeStringToInt(String input) {
        NumberValidate.validateChangeNumber(input);
        return StringUtil.convertStringToInt(input);
    }
}
