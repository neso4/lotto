package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Seller;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    InputView inputView;
    OutputView outputView;
    Seller seller;

    public LottoService() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.seller = new Seller();
    }

    public List<Lotto> purchaseLotto() {
        boolean purchased = false;
        List<Lotto> lottos = new ArrayList<>();

        while (!purchased) {
            try {
                int payment = inputView.requestPurchaseOfLotto();
                lottos = seller.sellLotto(payment);
                purchased = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        outputView.printPurchasedLottos(lottos);
        return lottos;
    }

    public WinningLotto createWinningLotto() {
        Lotto lotto = getWinningLottoOnlyNumbers();
        return getWinningLotto(lotto);
    }

    private WinningLotto getWinningLotto(Lotto lotto) {
        try {
            Integer bonusNumber = getBonusNumber();
            return new WinningLotto(lotto, bonusNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinningLotto(lotto);
        }
    }

    private Lotto getWinningLottoOnlyNumbers() {
        try {
            List<Integer> winningLottoNumbers = inputView.requestWinningLottoNumbers();
            return new Lotto(winningLottoNumbers);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinningLottoOnlyNumbers();
        }
    }

    private int getBonusNumber() {
        try {
            return inputView.requestBonusNumber();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getBonusNumber();
        }
    }
}
