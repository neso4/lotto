package lotto.view;

import lotto.domain.LottoCollector;

public class OutputView {
    private static final String TICKET_COUNT_MESSAGE = "개를 구매했습니다.";

    public static void printPublishedLottos(LottoCollector lottoCollector) {

        System.out.println();
        System.out.println(lottoCollector.getTicketCount() + TICKET_COUNT_MESSAGE);
        System.out.println(lottoCollector.convertLottoToString());
    }
}
