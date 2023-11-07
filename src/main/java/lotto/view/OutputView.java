package lotto.view;

import java.util.List;
import lotto.constant.ResultMessage;
import lotto.service.ResultDTO;

public class OutputView {
    private void printNumberOfLotto(int numberOfLottos) {
        System.out.println(numberOfLottos + "개를 구매했습니다.");
    }

    private void printLottoNumbers(List<List<Integer>> lottos) {
        lottos.forEach(x -> {
                    System.out.println("[" + String.join(",", x.toString()) + "]");
                });
    }

    public void printStatistic(List<Integer> fifthToFirst) {
        for(ResultMessage resultMessage : ResultMessage.values()) {
            System.out.printf(resultMessage.getMessage() + System.lineSeparator(), fifthToFirst.get(resultMessage.ordinal()));
        }
    }

    public void printProfitRate(double profitRate) {
        String profitRateToPrint = String.format("%.2f", profitRate);
        System.out.println("총 수익률은 " + profitRateToPrint + "입니다.");
    }

    public void printBoughtLottos(int numberOfLottos, List<List<Integer>> lottos) {
        printNumberOfLotto(numberOfLottos);
        printLottoNumbers(lottos);
    }

    public void printResult(ResultDTO resultDTO) {
        printStatistic(resultDTO.getResult());
        printProfitRate(resultDTO.getProfitRate());
    }
}
