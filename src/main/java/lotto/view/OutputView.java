package lotto.view;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Ranking;

public class OutputView {
    private static final String COUNT = "개";
    private static final String REQUEST_MONEY = "구입금액을 입력해 주세요.";
    private static final String BUY_LOTTO_COUNT = "를 구매했습니다.";
    private static final String REQUEST_WINNING_NUMBER = "당첨 번호를 입력해 주세요.";
    private static final String REQUEST_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";
    private static final String WINNING_RESULT = "당첨 통계";
    private static final String DASH_BAR = "---";

    private static final String EARNING_RATE_RESULT = "총 수익률은 ";

    public void displayRequestMoney() {
        System.out.println(REQUEST_MONEY);
    }

    public void displayBuyLottoCount(Lottos lottos, int tryNumber) {
        System.out.println(tryNumber + COUNT + BUY_LOTTO_COUNT);
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void displayRequestWinningNumber() {
        System.out.println(REQUEST_WINNING_NUMBER);
    }

    public void displayRequestBonusNumber() {
        System.out.println(REQUEST_BONUS_NUMBER);
    }

    public void displayResult() {
        System.out.println(WINNING_RESULT);
        System.out.println(DASH_BAR);
    }

    public void displayWinningLotto(List<Integer> ranks) {
        for (int i = 5; i > 0; i--) {
            int winning_count = ranks.get(5 - i);
            System.out.println(Ranking.getMatchRankMessage(i) + winning_count + COUNT);
        }
    }

    public void displayEarningRate(float earningRate) {
        System.out.print(EARNING_RATE_RESULT + String.format("%.1f", earningRate) + "%입니다.");
    }


    public void displayERRORMESSAGE(String message) {
        System.out.println(message);
    }
}
