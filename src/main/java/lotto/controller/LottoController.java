package lotto.controller;

import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final LottoService lottoService;

    public LottoController() {
        lottoService = new LottoService();
    }

    public void runLottoGame() {
        // 구입 금액 입력
        OutputView.printRequestPurchaseAmount();
        InputView.readLine();

        // 로또 구매 결과 출력
        OutputView.printResponsePurchaseAmount(1);

        // 당첨 번호 입력
        OutputView.printRequestWinningNumber();
        InputView.readLine();

        // 보너스 번호 입력
        OutputView.printRequestBonusNumber();
        InputView.readLine();

        // 당첨 통계 출력
    }

}
