package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Buyer;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;
import lotto.validator.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {
    private Buyer buyer;
    private WinningNumbers winningNumbers;
    private BonusNumber bonusNumber;

    private void getPurchasePrice() {
        String inputPurchasePrice = askPurchasePrice();
        inputPurchasePrice = removeComma(inputPurchasePrice);
        checkInputPurchasePriceValidation(inputPurchasePrice);
        buyer = new Buyer(Integer.parseInt(inputPurchasePrice));
    }

    private String askPurchasePrice() {
        InputView.showAskPurchasePrice();
        return Console.readLine().trim();
    }

    private String removeComma(String inputPurchasePrice) {
        if (inputPurchasePrice.contains(",")) {
            inputPurchasePrice = inputPurchasePrice.replace(",", "");
        }
        return inputPurchasePrice;
    }

    private void checkInputPurchasePriceValidation(String inputPurchasePrice) {
        InputValidator.isNullOrIsEmpty(inputPurchasePrice);
        InputValidator.isNotDigit(inputPurchasePrice);
        InputValidator.isNotPositiveNumber(inputPurchasePrice);
    }

    private void getPurchaseLotteriesInformation(int lottoQuantity) {
        for (int i = 0; i < lottoQuantity; i++) {
            buyer.addPurchaseLotto(new Lotto(generateLottoNumbers()));
        }
        showLottoQuantity();
        showPurchaseLotteries(buyer.getPurchaseLotteries());
    }

    private List<Integer> generateLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }

    private void showLottoQuantity() {
        OutputView.showLottoQuantity(buyer.getLottoQuantity());

    }

    private void showPurchaseLotteries(List<Lotto> purchaseLotteries) {
        for (Lotto purchaseLotto : purchaseLotteries) {
            OutputView.showPurchaseLotto(purchaseLotto.getNumbers()
                                                        .stream()
                                                        .sorted()
                                                        .toList());
        }
    }

    private void getWinningNumbers() {
        String inputWinningNumbers = askWinningNumbers();
        List<String> isSplitWinningNumbers = splitWinningNumbers(inputWinningNumbers);
        checkInputValidation(inputWinningNumbers, isSplitWinningNumbers);
        winningNumbers = new WinningNumbers(convertedWinningNumbers(isSplitWinningNumbers));
    }

    private String askWinningNumbers() {
        InputView.showAskWinningNumbers();
        return Console.readLine().trim();
    }

    private List<String> splitWinningNumbers(String inputWinningNumbers) {
        return Arrays.asList(inputWinningNumbers.split(","));
    }

    private void checkInputValidation(String inputWinningNumbers, List<String> isSplitWinningNumbers) {
        InputValidator.isNullOrIsEmpty(inputWinningNumbers);
        InputValidator.includeSymbolExceptComma(inputWinningNumbers);
        InputValidator.isNotDigits(isSplitWinningNumbers);
    }

    private List<Integer> convertedWinningNumbers(List<String> isSplitWinningNumbers) {
        List<Integer> winningNumbers = new ArrayList<>();
        for (String number : isSplitWinningNumbers) {
            winningNumbers.add(Integer.parseInt(number.trim()));  // 구분자 뒤 공백 제거
        }
        return winningNumbers;
    }

    private void getBonusNumber() {
        String inputBonusNumber = askBonusNumber();
        checkInputValidation(inputBonusNumber);
        bonusNumber = new BonusNumber(Integer.parseInt(inputBonusNumber));
    }

    private String askBonusNumber() {
        InputView.showAskBonusNumber();
        return Console.readLine().trim();
    }

    private void checkInputValidation(String inputBonusNumber) {
        InputValidator.isNullOrIsEmpty(inputBonusNumber);
        InputValidator.isNotDigit(inputBonusNumber);
    }
}
