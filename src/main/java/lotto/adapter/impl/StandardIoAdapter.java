package lotto.adapter.impl;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.adapter.IoAdapter;
import lotto.domain.Statistics;
import lotto.vo.Lotto;

public class StandardIoAdapter implements IoAdapter {

    @Override
    public String inputStream() {
        return Console.readLine();
    }

    @Override
    public void printPurchaseLotto(List<Lotto> lottoBundle) {
    }

    @Override
    public void printStatistics(Statistics statistics) {

    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printLotto(List<Integer> lotto) {
        System.out.println(lotto);
    }
}
