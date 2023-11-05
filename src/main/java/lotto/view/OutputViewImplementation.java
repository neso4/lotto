package lotto.view;

import static lotto.enumerate.Message.LOTTO_BUYED;

import java.util.List;

public class OutputViewImplementation implements OutputView {
    @Override
    public void printLottoList(int lottoListNumber, List<String> lottoListString) {
        System.out.printf((LOTTO_BUYED.getMessage()), lottoListNumber);
        lottoListString.forEach(System.out::println);
    }

    @Override
    public void printWinningList(String winningList) {
        System.out.println();
        System.out.printf(winningList);
    }

    @Override
    public void printProfitRate(String profitRate) {
        System.out.println(profitRate);
    }
}
