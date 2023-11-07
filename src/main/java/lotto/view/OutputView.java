package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.enums.LottoStatus;
import lotto.service.LottoDto;
import lotto.service.LottoResultDto;

public class OutputView {
    public static final String WINNING_STATISTICS = "당첨 통계";

    public static void printError(String message) {
        System.out.println(message);
    }

    public static void displayLottos(List<LottoDto> lottoDtos) {
        printEmptyLine();
        System.out.println(lottoDtos.size() + "개를 구매했습니다.");
        for (LottoDto lottoDto : lottoDtos) {
            System.out.println(lottoDto.numbers());
        }
        printEmptyLine();
    }

    public static void displayResult(LottoResultDto lottoResultDto) {
        Map<LottoStatus, Integer> lottoStatusCounts = lottoResultDto.lottoStatusCounts();
        printEmptyLine();
        StringBuilder lottoResult = new StringBuilder();
        lottoResult.append(WINNING_STATISTICS).append("\n");
        lottoResult.append("---").append("\n");

        for (LottoStatus status : LottoStatus.values()) {
            if (status != LottoStatus.SIXTH) {
                lottoResult.append(status.getStatusMessage()).append(" - ").append(lottoStatusCounts.get(status))
                        .append("개")
                        .append("\n");
            }
        }

        System.out.println(lottoResult);
    }

    public static void printEmptyLine() {
        System.out.println();
    }


    public static void displayProfitRate(double profitRate) {
        System.out.println("총 수익률은 " + profitRate + "%입니다.");
    }

}
