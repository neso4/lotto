package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lotto.dto.LottoResultNotifier;
import lotto.model.Lotto;
import lotto.model.LottoDraw;
import lotto.model.Lottos;
import lotto.service.LotteryKiosk;
import lotto.util.LottoResult;
import lotto.view.UserInputOutput;

public class LottoController {
    private final LotteryKiosk lotteryKiosk;

    private final UserInputOutput userInputOutput = new UserInputOutput();

    public LottoController(LotteryKiosk lotteryKiosk) {
        this.lotteryKiosk = lotteryKiosk;
    }

    public void run() {
        Lottos lottos = buyLottoTicket();
        showIssuedLottoNumbers(lottos);
        LottoDraw winningNumbers = getWinningNumbers();
        LottoResultNotifier resultNotifier = new LottoResultNotifier(lottos, winningNumbers);
        resultNotifier(resultNotifier);
    }


    private Lottos buyLottoTicket() {
        while (true) {
            try {
                String amount = userInputOutput.enterPurchaseAmount();
                int purchaseAmount = Integer.parseInt(amount);
                return lotteryKiosk.publish(purchaseAmount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showIssuedLottoNumbers(Lottos lottos) {
        List<Lotto> lottoBundle = lottos.lottoBundle();
        userInputOutput.printLottoNum(lottoBundle.size());
        for (Lotto lotto : lottoBundle) {
            userInputOutput.printLotto(lotto);
        }
    }

    private LottoDraw getWinningNumbers() {
        while (true) {
            try {
                String winningNumbers = userInputOutput.enterWinningNumbers();
                int bonusNum = Integer.parseInt(userInputOutput.enterBonusNumber());
                List<Integer> numbers = Arrays.stream(winningNumbers.split(","))
                        .map(Integer::parseInt)
                        .toList();
                return new LottoDraw(numbers, bonusNum);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void resultNotifier(LottoResultNotifier resultNotifier) {
        userInputOutput.printResultStatistics();
        List<Map.Entry<LottoResult, Integer>> entries = resultNotifier.sortByAscendingWinnings();
        for (Entry<LottoResult, Integer> entry : entries) {
            if (entry.getKey() == LottoResult.NO_PRIZE) {
                continue;
            }
            userInputOutput.printResultLottoCount(entry.getKey().getContent(), entry.getValue());
        }
        userInputOutput.printRateOfReturn(resultNotifier.getRateOfReturn());
    }

}
