package lotto.models;

import java.util.HashMap;

public class LottoResult {

    private final HashMap<LottoGrade, Integer> lottoResult = new HashMap<>();
    private final int lottoAmount;

    public LottoResult(int lottoAmount) {
        this.lottoAmount = lottoAmount;
    }

    public void addLottoResult(LottoGrade lottoGrade) {
        lottoResult.put(lottoGrade, lottoResult.getOrDefault(lottoGrade, 0) + 1);
    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();

        for (LottoGrade lottoGrade : LottoGrade.values()) {
            if (lottoGrade == LottoGrade.NONE) {
                continue;
            }

            content.append(lottoGrade.toString())
                    .append(" - ")
                    .append(lottoResult.getOrDefault(lottoGrade, 0))
                    .append("개\n");
        }

        content.append("총 수익률은 ")
                .append(calcProfitRate())
                .append("%입니다.");

        return content.toString();
    }

    private double calcProfitRate() {
        double initialMoney = lottoAmount * 1000;
        double totalPrizeMoney = 0;

        for (LottoGrade lottoGrade : LottoGrade.values()) {
            totalPrizeMoney += lottoGrade.getPrizeMoney() * lottoResult.getOrDefault(lottoGrade, 0);
        }

        return Math.round(totalPrizeMoney / initialMoney * 100 * 100) / 100.0;
    }
}
