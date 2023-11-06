package lotto.view;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import lotto.domain.Lotteries;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OutputViewTest {

    OutputView outputView = new OutputView();

    Lotteries lotteries;

    @BeforeEach
    void init() {
        lotteries = Lotteries.from(5);
    }

    @Test
    void printLottoNumbers() {
        List<List<Integer>> ex = lotteries.getNumbersOfLotteries();
        outputView.printLottoNumbers(ex);
    }

    @Test
    void printPurchaseAmount() {
        outputView.printPurchaseAmount(3);
    }

    @Test
    void printEarningRate() {
        outputView.printEarningRate(62.5);
    }

    @Test
    void testGameResult() {
        Lotteries ex2 = Lotteries.from(5);
        System.out.println(lotteries.getNumbersOfLotteries());
        WinningLotto lotto = WinningLotto.of("1,2,3,4,5,6", "7");
        LottoResult lottoResult = LottoResult.from();
        lottoResult.countWinningCase(ex2.getLotteries(), lotto);
        System.out.println(lottoResult.getResultMap());
    }
}