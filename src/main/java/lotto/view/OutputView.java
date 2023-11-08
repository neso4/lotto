package lotto.view;


import lotto.model.Lotto;
import lotto.model.Result;

import java.util.List;

import static lotto.message.OutputMessage.*;

public class OutputView {
    private OutputView() {
    }

    private static class OutputViewHelper {
        private final static OutputView OUTPUT_VIEW = new OutputView();
    }

    public static OutputView getInstance() {
        return OutputViewHelper.OUTPUT_VIEW;
    }

    public void printLottos(List<Lotto> lottos) {
        System.out.printf("%d" + COUNT.getMessage() + "\n", lottos.size());
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void result(Result result) {
        System.out.println(STATICS.getMessage());
        System.out.printf(FIFTH.getMessage() +
                        FOURTH.getMessage() +
                        THIRD.getMessage() +
                        SECOND.getMessage() +
                        FIRST.getMessage(),
                result.getFifth(), result.getFourth(), result.getThird(), result.getSecond(), result.getFirst());
    }

    public void revenueRate(double revenueRate) {
        System.out.printf(REVENUE.getMessage(), (revenueRate+"%"));
    }
}