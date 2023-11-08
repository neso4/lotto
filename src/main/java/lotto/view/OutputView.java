package lotto.view;

import lotto.domain.Lottos;

public class OutputView {

    private static final String LOTTO_PURCHASE_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String LOTTO_COUNT_MESSAGE = "%d개를 구매했습니다.";

    public static void printLottoPurchaseMoneyMessage() {
        System.out.println(LOTTO_PURCHASE_MONEY_MESSAGE);
    }

    public static void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printLottos(Lottos lottos) {
        lottos.getLottos()
            .forEach(l -> System.out.println(l.getLottoNumbers()));
        printEmptyLine();
    }

    public static void printLottoCountMessage(int lottoCount) {
        System.out.println(LOTTO_COUNT_MESSAGE.formatted(lottoCount));
    }
}
