package lotto.models;

import java.util.HashMap;
import java.util.List;

public class LottoResult {

    private final HashMap<LottoGrade, Integer> lottoResultMap = new HashMap<>();
    private final int lottoAmount;

    public LottoResult(int lottoAmount) {
        this.lottoAmount = lottoAmount;
    }

    public void addLottoResult(LottoGrade lottoGrade) {
        lottoResultMap.put(lottoGrade, lottoResultMap.getOrDefault(lottoGrade, 0) + 1);
    }

    public void addLottoResult(List<LottoGrade> lottoGrades) {
        for (LottoGrade lottoGrade : lottoGrades) {
            addLottoResult(lottoGrade);
        }
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
                    .append(lottoResultMap.getOrDefault(lottoGrade, 0))
                    .append("개\n");
        }

        content.append("총 수익률은 ")
                .append(calcProfitRate())
                .append("%입니다.");

        return content.toString();
    }

    public double calcProfitRate() {
        double initialMoney = lottoAmount * 1000;
        double totalPrizeMoney = 0;

        for (LottoGrade lottoGrade : LottoGrade.values()) {
            totalPrizeMoney += lottoGrade.getPrizeMoney() * lottoResultMap.getOrDefault(lottoGrade, 0);
        }

        return Math.round(totalPrizeMoney / initialMoney * 100 * 100) / 100.0;
    }

    public HashMap<LottoGrade, Integer> getLottoResultMap() {
        return lottoResultMap;
    }

    public int getLottoAmount() {
        return lottoAmount;
    }

    public int getLottoResultAmount(LottoGrade lottoGrade) {
        return getLottoResultMap().getOrDefault(lottoGrade, 0);
    }
}
