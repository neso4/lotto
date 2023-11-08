package view;

import dto.LottoTickets;
import java.util.List;

public class View {
    private final InputView inputView;
    private final OutputView outputView;

    public View(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public int requirePurchaseAmount() {
        outputView.requestPurchaseAmount();
        return inputView.enterPurchaseAmount();
    }

    public void showLottoTickets(LottoTickets lottoTickets) {
        outputView.printLottoTickets(lottoTickets);
    }

    public List<Integer> requireLottoWinningNumbers() {
        outputView.requestLottoWinningNumbers();
        return inputView.enterWinningLottoNumbers();
    }
}
