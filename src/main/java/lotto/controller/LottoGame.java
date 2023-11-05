package lotto.controller;

import java.util.HashMap;
import java.util.List;
import lotto.domain.Buyer;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.service.LottoService;
import lotto.utils.InputUtil;
import lotto.utils.LottoUtils;
import lotto.validation.Validation;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {
    private final LottoService lottoService = new LottoService();
    private final HashMap<Rank, Integer> result = new HashMap<>();
    private long payment;
    private Buyer buyer;
    private WinningLotto winningLotto;
    private long reward;
    public void run() {
        buyLotto();
        initWinningLotto(inputWinningLotto(), inputBonusNumber());
//        matchLottos();
//        showResult();
    }

    private List<Integer> inputWinningLotto() {
        InputView.inputNumbers();
        return LottoUtils.stringToList(InputUtil.inputStringWithTrim());
    }

    private String inputBonusNumber() {
        InputView.inputBonusNumber();
        return InputUtil.inputStringWithTrim();
    }

    private void initWinningLotto(List<Integer> lotto, String bonus) {
        winningLotto = new WinningLotto(lotto, bonus);
    }

    private void buyLotto() {
        InputView.inputPrise();
        buyer = new Buyer(InputUtil.inputStringWithTrim());
        buyer.showLottos();
    }
}
