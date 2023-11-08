package lotto.controller;

import lotto.domain.*;
import lotto.util.Validation;
import lotto.view.UserView;

import java.util.Arrays;
import java.util.List;

public class LottoController {

    private LottoWallet lottoWallet;
    private LottoMachine lottoMachine;
    private final LottoManager lottoManager;

    public LottoController() {
        this.lottoManager = LottoManager.getInstance();
        this.lottoMachine = new LottoMachine();
    }
    public void playLotto() {
        // 구입금액 입력 및 지갑 생성
        createWallet();

        // 로또 구매
        purchaseLottos();

        //당첨 번호 입력
        initWinningNumbers();

        //내 지갑의 당첨번호 확인
        checkMyLottos();

        printResult();
    }

}
