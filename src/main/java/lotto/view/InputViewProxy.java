package lotto.view;

import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.PurchaseCost;
import lotto.util.ExceptionHandler;

public class InputViewProxy {

    private final InputView inputView;

    public InputViewProxy(InputView inputView) {
        this.inputView = inputView;
    }

    public PurchaseCost getMoney() {
        return ExceptionHandler.handle(inputView::getMoney);
    }

    public Lotto getWinningNumbers() {
        return ExceptionHandler.handle(inputView::getWinningNumbers);
    }

    public BonusNumber getBonusNumber() {
        return ExceptionHandler.handle(inputView::getBonusNumber);
    }
}
