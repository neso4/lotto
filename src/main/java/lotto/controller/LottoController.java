package lotto.controller;

import java.util.Map.Entry;
import lotto.model.Client;
import lotto.model.Lotto;
import lotto.model.LottosResult;
import lotto.model.WinningNumbers;
import lotto.model.LottoStore;
import lotto.model.constans.WinningRank;
import lotto.view.View;

public class LottoController {
    private static final View view = new View();
    private static final LottoStore lottoStore = new LottoStore();

    public void run() {
        Client client = buyLottos();
        issueLottos(client);
        WinningNumbers winningNumbers = drawWinningNumbers();
        announceLottoResults(client, winningNumbers);
    }

    private Client buyLottos() {
        view.printPayAmountInputMessage();
        while (true) {
            try {
                String payAmount = view.inputValue();
                return Client.from(payAmount);
            } catch (IllegalArgumentException e) {
                view.printExceptionMessage(e);
            }
        }
    }

    private void issueLottos(Client client) {
        int purchasedLottoAmount = lottoStore.calculatePurchasedLottoAmount(client.getPayAmount());
        view.printPurchasedLottoAmount(purchasedLottoAmount);
        for (int issuedLottoCount = 1; issuedLottoCount <= purchasedLottoAmount; issuedLottoCount++) {
            Lotto lotto = lottoStore.issueRandomLotto();
            view.printIssuedLotto(lotto.toString());
            client.receiveLotto(lotto);
        }
    }

    private WinningNumbers drawWinningNumbers() {
        WinningNumbers winningNumbers = createWinningNumbers();
        createBonusNumber(winningNumbers);
        return winningNumbers;
    }

    private WinningNumbers createWinningNumbers() {
        view.printWinningNumbersInputMessage();
        while (true) {
            try {
                String winningNumbers = view.inputValue();
                return WinningNumbers.from(winningNumbers);
            } catch (IllegalArgumentException e) {
                view.printExceptionMessage(e);
            }
        }
    }

    private void createBonusNumber(WinningNumbers winningNumbers) {
        view.printBonusNumberInputMessage();
        while (true) {
            try {
                String bonusNumber = view.inputValue();
                winningNumbers.createBonusNumber(bonusNumber);
                break;
            } catch (IllegalArgumentException e) {
                view.printExceptionMessage(e);
            }
        }
    }

    private void announceLottoResults(Client client, WinningNumbers winningNumbers) {
        LottosResult lottoResults = winningNumbers.calculateLottosResult(client.getLottos());
        view.printWinningStatisticMessage();
        printLottoResult(lottoResults);
        double rateOfReturn = client.calculateRateOfReturn(lottoResults);
        view.printRateOfReturn(rateOfReturn);
    }

    private void printLottoResult(LottosResult lottosResult) {
        for (WinningRank winningRank : lottosResult) {
            if (winningRank.equals(WinningRank.NO_PRIZE)) {
                continue;
            }
            view.printLottoResult(winningRank.toString(), lottosResult.get(winningRank));
        }
    }
}
