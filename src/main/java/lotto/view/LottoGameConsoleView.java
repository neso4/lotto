package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.controller.LottoGameView;
import lotto.controller.dto.LottoBonusNumberCreateRequest;
import lotto.controller.dto.LottoPurchaseRequest;
import lotto.controller.dto.LottoResponse;
import lotto.controller.dto.LottoWinningNumberCreateRequest;
import lotto.controller.dto.LottoWinningStatistics;
import lotto.domain.LottoPrize;

public class LottoGameConsoleView implements LottoGameView {

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    @Override
    public LottoPurchaseRequest inputPurchaseRequest() {
        System.out.println("구입금액을 입력해 주세요.");
        String purchaseAmount = Console.readLine();
        return new LottoPurchaseRequest(purchaseAmount);
    }

    @Override
    public void printPurchasedTickets(List<LottoResponse> purchasedLottoTickets) {
        long numLottoTickets = purchasedLottoTickets.size();

        System.out.println("\n" + numLottoTickets + "개를 구매했습니다.");

        for (LottoResponse lottoTicket : purchasedLottoTickets) {
            System.out.println(lottoTicket.getNumbers());
        }
    }

    @Override
    public LottoWinningNumberCreateRequest inputLottoWinningNumberCreateRequest() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
        return new LottoWinningNumberCreateRequest(Console.readLine());
    }

    @Override
    public LottoBonusNumberCreateRequest inputLottoBonusNumberCreateRequest() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        return new LottoBonusNumberCreateRequest(Console.readLine());
    }

    @Override
    public void printWinningStatistics(LottoWinningStatistics lottoWinningStatistics) {
        System.out.println("\n당첨 통계");
        System.out.println("---");

        printLottoPrizeCount(lottoWinningStatistics);
        printRewardRatioPercent(lottoWinningStatistics);
    }

    private static void printLottoPrizeCount(LottoWinningStatistics lottoWinningStatistics) {
        for (LottoPrize lottoPrize : LottoPrize.values()) {
            if (lottoPrize.equals(LottoPrize.NONE)) {
                continue;
            }

            int countLottoPrize = lottoWinningStatistics.getCountLottoPrize(lottoPrize);
            System.out.println(lottoPrize.toString() + countLottoPrize + "개");
        }
    }

    private static void printRewardRatioPercent(LottoWinningStatistics lottoWinningStatistics) {
        double rewardRatio = lottoWinningStatistics.getRewardRatio();
        double rewardRatioPercent = (double) Math.round(rewardRatio * 1000) / 10;
        System.out.printf("총 수익률은 %.1f%%입니다.\n", rewardRatioPercent);
    }

    @Override
    public void printException(Exception exception) {
        String message = ERROR_MESSAGE_PREFIX + exception.getMessage();
        System.out.println("\n" + message + "\n");
    }
}
