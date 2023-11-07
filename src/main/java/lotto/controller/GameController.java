package lotto.controller;

import java.util.List;
import lotto.LottoGenerator;
import lotto.Validator.LottoValidator;
import lotto.domain.Lotto;
import lotto.domain.LottoBuyer;
import lotto.domain.LottoManager;
import lotto.view.InputView;
import lotto.view.OutputView;

public class GameController {

    public void run() {

        int lottoQuantity = purchaseLottoQuantity();
        List<Lotto> lottoTickets = LottoGenerator.createLottoTickets(lottoQuantity);

        LottoBuyer lottoBuyer = new LottoBuyer(lottoTickets);

        OutputView.announceLottoPurchaseQuantity(lottoBuyer.getPurchaseQuantity());
        OutputView.announceMultipleLottoNumbers(lottoBuyer.getLottoTickets());

        List<Integer> winningLottoNumbers = requestWinningLottoNumbers();
        LottoManager lottoManager = new LottoManager(winningLottoNumbers);

        countMatchingCounts(lottoBuyer, lottoManager);


    }

    private void countMatchingCounts(LottoBuyer lottoBuyer, LottoManager lottoManager) {
        lottoBuyer.getLottoTickets().forEach(ticket -> {
            int count = (int) lottoManager.getWinningLottoNumbers().stream()
                    .filter(ticket.getNumbers()::contains)
                    .count();
            lottoManager.addMatchingCount(count);
        });
    }

    private int purchaseLottoQuantity() {
        while (true) {
            try {
                int purchaseAmount = InputView.requestLottoPurchaseAmount();// requestWinningLottoNumbers 처럼 내부로직에서 parseInt를 다루지 않을 것 인지에 대해 고민해봐야함.
                LottoValidator.isValidPurchaseAmount(purchaseAmount);

                return purchaseAmount / 1000;
            } catch (IllegalArgumentException e) {
                System.err.println("[ERROR] 구입 금액은 1000원 단위여야 합니다. 구입금액을 다시 입력해주세요.");
            }
        }
    }

    private List<Integer> requestWinningLottoNumbers() {
        while(true) {
            try {
                List<Integer> winningLottoNumbers = InputView.requestWinningLottoNumbers();
                LottoValidator.isValidWinningLottoNumbers(winningLottoNumbers);

                return winningLottoNumbers;
            } catch (IllegalArgumentException e) {
                System.err.println("[ERROR] 번호의 개수는 6개, 범위는 1~45 사이이며 중복된 숫자가 없어야 합니다. 당첨 번호를 다시 입력해주세요.");
            }
        }
    }


}
