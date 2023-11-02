package lotto.io;

import lotto.constant.ProgressMessage;
import lotto.domain.Lottos;
import lotto.formatter.OutputFormatter;

public class OutputView {

    private final OutputFormatter outputFormatter;

    public OutputView(final OutputFormatter outputFormatter) {
        this.outputFormatter = outputFormatter;
    }

    public void printPurchaseAmountRequest() {
        System.out.println(ProgressMessage.PURCHASE_AMOUNT_REQUEST.toValue());
    }

    public void printLottos(final Lottos lottos) {
        System.out.println(
                String.format(
                        ProgressMessage.PUBLISHED_NUM_OF_LOTTO.toValue(), lottos.numOfElement()));
        System.out.println(outputFormatter.formatLottos(lottos));
    }
}
