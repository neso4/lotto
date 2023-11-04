package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoController {
    public static void start() {
        int purchaseAmount = InputView.getUserPurchaseAmount();
        int lottoTicketCount = purchaseAmount / 1000;
        LottoView.printLottoTicketCount(lottoTicketCount);

        List<List<Integer>> lottoTickets = generateLottoTickets(lottoTicketCount);
        System.out.println();
        List<Integer> lottoNumbers = InputView.getWinningNumbers();
        int bonusNumber = InputView.getBonusNumber(lottoNumbers);

        LottoModel model = new LottoModel(lottoTickets, lottoNumbers, bonusNumber, purchaseAmount);
        model.calculatePrize();
        model.displayStatistics();
    }

    private static List<List<Integer>> generateLottoTickets(int ticketCount) {
        List<List<Integer>> allLotto = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            List<Integer> oneLotto = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            Collections.sort(oneLotto);
            OutputView.printLottoTickets(oneLotto);
            allLotto.add(oneLotto);
        }
        return allLotto;
    }
}

