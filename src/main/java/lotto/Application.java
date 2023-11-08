package lotto;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) throws IllegalArgumentException {
        LottoStore lottoStore = new LottoStore();

        lottoStore.purchaseLotto();
        lottoStore.printLottos();
        lottoStore.getWinningNumber();
        lottoStore.getBonusNumber();
        lottoStore.caculatePrize();
        lottoStore.printPrize();
        double returnRate = lottoStore.calculateReturn();
        lottoStore.printReturn(returnRate);
    }
}
