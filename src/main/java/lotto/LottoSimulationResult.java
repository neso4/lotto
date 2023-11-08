package lotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoSimulationResult {
    private static final String TEXT_RETURN_FORMAT = "총 수익률은 %s%%입니다.";
    Map<LottoMatchResult, Integer> resultCount;

    public LottoSimulationResult() {
        this.resultCount = new HashMap<>();

        for (LottoMatchResult result : LottoMatchResult.values()) {
            resultCount.put(result, 0);
        }
    }

    public void add(LottoMatchResult result) {
        int value = resultCount.get(result);
        resultCount.put(result, value + 1);
    }

    public double calculateReturn() {
        int totalAmount = 0, returnAmount = 0;

        for (LottoMatchResult matchResult : LottoMatchResult.values()) {
            totalAmount += LottoSimulator.LOTTO_PRICE * resultCount.get(matchResult);
            returnAmount += matchResult.getReward() * resultCount.get(matchResult);
        }

        return (double) returnAmount / totalAmount;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (LottoMatchResult matchResult : LottoMatchResult.values()) {
            if (matchResult == LottoMatchResult.UNMATCHED) {
                continue;
            }

            result.append(String.format("%s - %d개\n", matchResult, resultCount.get(matchResult)));
        }

        Double roundedReturn = (double) Math.round(calculateReturn() * 10000) / 100;
        result.append(String.format(TEXT_RETURN_FORMAT, roundedReturn.toString()));

        return result.toString();
    }
}
