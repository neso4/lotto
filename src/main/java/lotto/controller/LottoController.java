package lotto.controller;

import lotto.domain.*;
import lotto.model.LottoGame;
import lotto.model.User;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Map;

public class LottoController {
    InputView inputView;
    OutputView outputView;
    User user;
    LottoGame lottoGame;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.user = new User();
        this.lottoGame = new LottoGame();
    }

    public void run() {
        Price price = getPrice();
        UserLottos userLottos = issueLotto(price);
        LottoWinning lottoWinning = getWinningNumbers();
        printLottoResult(userLottos, lottoWinning, price);
    }

    // 구매 가격 받아 오기
    private Price getPrice() {
        inputView.requestPurchasePrice();
        return user.requestPurchasePrice();
    }

    // 로또 발행 하고 출력
    private UserLottos issueLotto(Price price) {
        UserLottos userLottos = lottoGame.generateAllLottos(price);
        outputView.printNumberOfPurchase(userLottos.getLottos().size());
        outputView.printAllLottoNumbersList(userLottos);

        return userLottos;
    }

    // 당첨 번호와 보너스 번호 받아오기
    private LottoWinning getWinningNumbers() {
        // 당첨 번호와 보너스 번호 입력 받기
        inputView.requestWinningNum();
        Lotto winningNums = user.requestWinningNums();

        inputView.requestBonusNum();
        LottoNumber bonusNum = user.requestBonusNum(winningNums);

        LottoWinning lottoWinning = new LottoWinning(winningNums, bonusNum);
        return lottoWinning;
    }

    // 당첨 결과와 수익률 출력
    private void printLottoResult(UserLottos userLottos, LottoWinning lottoWinning, Price price) {
        Map<Integer, Integer> rankMap = lottoGame.calcWinningStatistics(userLottos, lottoWinning);
        RateOfReturn rateOfReturn = lottoGame.calcRateOfReturn(rankMap, price);

        outputView.printWinningStatistics(rankMap, rateOfReturn);
    }
}
