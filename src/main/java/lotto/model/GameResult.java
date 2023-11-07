package lotto.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.constant.GradeConstant;

public class GameResult {

    private final Map<GradeConstant, Integer> gradeResult;
    private final double profitRate;

    public GameResult(List<GradeConstant> gradeConstants, int purchaseMoney) {
        gradeResult = new HashMap<>();
        initGradeResult(gradeConstants);

        profitRate = initProfitRate(gradeConstants, purchaseMoney);
    }

    private void initGradeResult(List<GradeConstant> gradeConstants) {
        for (GradeConstant gradeConstant : gradeConstants) {
            gradeResult.put(gradeConstant, gradeResult.getOrDefault(gradeConstant, 0) + 1);
        }
    }

    private double initProfitRate(List<GradeConstant> gradeConstants, int purchaseMoney) {
        long total = getTotalPrize(gradeConstants);
        return (100 * total) / (1.0 * purchaseMoney);
    }

    private long getTotalPrize(List<GradeConstant> gradeConstants) {
        return gradeConstants.stream()
                .mapToLong(GradeConstant::prize)
                .sum();
    }

    public Map<GradeConstant, Integer> getGrades() {
        return Collections.unmodifiableMap(gradeResult);
    }

    public double getProfitRate() {
        return profitRate;
    }
}