package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.domain.Prize;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

public class LottoController {

    private static EnumMap<Prize, Integer> prizeMap = new EnumMap<Prize, Integer>(Prize.class);

    public void runLotto() {
        PurchaseAmount purchaseAmount = payMoney();
        List<Lotto> issuedLottoList = issueLottoList(purchaseAmount);
        WinningLotto winningLotto = selectWinningLottoNumbers();
        compareWithWinningNum(issuedLottoList, winningLotto);
        LottoResult();
        calculate(purchaseAmount);
    }

    private PurchaseAmount payMoney() {
        int money = 0;
        try {
            money = InputView.inputPurchaseAmount();
            return new PurchaseAmount(money);
        } catch(IllegalArgumentException e) {
            System.out.println("");
            return payMoney();
        }
    }

    private WinningLotto selectWinningLottoNumbers() {
        try {
            List<Integer> winningSixNumbers = InputView.inputWinnerNumbers();
            int winningBonusNumbers = InputView.inputBonusNumbers();
            return new WinningLotto(new Lotto(winningSixNumbers), winningBonusNumbers);
        } catch(IllegalArgumentException e) {
            System.out.println("");
            return selectWinningLottoNumbers();
        }
    }

    private List<Lotto> issueLottoList(PurchaseAmount purchaseAmount) {
        List<Lotto> lottoList = new ArrayList<>();
        int lottoCount = purchaseAmount.getLottoCount();
        OutputView.outputLottoCount(lottoCount);
        for (int i=0; i<lottoCount; i++) {
            Lotto lotto = new Lotto();
            OutputView.outputIssuedLotto(lotto);
            lottoList.add(lotto);
        }
        return lottoList;
    }

    private void compareWithWinningNum(List<Lotto> lottoList, WinningLotto winningLotto) {
        boolean isEqualWithBonus = false;
        initalizeEnumMap();
        for (int i=0; i<lottoList.size(); i++) {
            int equalCount = lottoList.get(i).compareWithLotto(winningLotto);
            if (equalCount == 5) {
                isEqualWithBonus = lottoList.get(i).compareWithBonusNum(winningLotto);
            }
            Prize prize = Prize.rankLotto(equalCount, isEqualWithBonus);
            prizeMap.put(prize, prizeMap.getOrDefault(prize, 0)+1);
        }
    }

    private void initalizeEnumMap() {
        for (Prize prize : Prize.values()) {
            prizeMap.put(prize, 0);
        }
    }

    private void LottoResult() {
        OutputView.outputWinningInfo(prizeMap);
    }

    private void calculate(PurchaseAmount purchaseAmount) {
        long totalRevenue = 0;
        for (Prize prize : prizeMap.keySet()) {
            int winningCnt = prizeMap.get(prize);
            totalRevenue += (winningCnt * prize.getWinningAmount());
        }
        OutputView.outputRateOfReturn(String.format("%.2f", (double)(totalRevenue*100)/purchaseAmount.getMoney()));
    }
}
