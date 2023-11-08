package lotto.Output;

import lotto.Controller.LottoResultEnum;

import java.util.List;

public class Result {
    private final Integer price;
    private final List<Integer> lottoResult;

    public Result(Integer price, List<Integer> lottoResult) {
        this.price = price;
        this.lottoResult = lottoResult;
        printResult(price, lottoResult);
    }

    public void printResult(Integer price, List<Integer> lottoResult) {
        int benefit = 0;
        for (LottoResultEnum lottoResultEnum : LottoResultEnum.values()) {
            System.out.println(lottoResultEnum.getResult() + lottoResult.get(lottoResultEnum.getMatch()) + "개");
            benefit += lottoResultEnum.getPrize() * lottoResult.get(lottoResultEnum.getMatch());
        }
        Double profit = (double) benefit / price;
        System.out.println("총 수익률은 " + Math.round(profit * 10000.0) / 100.0 + "%입니다.");
    }
}
