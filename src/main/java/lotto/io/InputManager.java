package lotto.io;

import lotto.domain.Amount;
import lotto.domain.BonusNumber;
import lotto.domain.WinningLotto;

public class InputManager {

    private final InputView inputView;
    private final InputMapper inputMapper;

    public InputManager(final InputView inputView, final InputMapper inputMapper) {
        this.inputView = inputView;
        this.inputMapper = inputMapper;
    }

    public Amount readPurchaseAmount() {
        final String input = inputView.readPurchaseAmount();
        return inputMapper.toAmount(input);
    }

    public WinningLotto readWinningLotto() {
        final String input = inputView.readWinningLottoNumber();
        return new WinningLotto(inputMapper.toWinningLotto(input));
    }

    public BonusNumber readBonusNumber() {
        final String input = inputView.readBonusNumber();
        return inputMapper.toBonusNumber(input);
    }
}
