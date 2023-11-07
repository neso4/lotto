package lotto;

import java.util.Arrays;
import java.util.HashMap;

public enum WinningCase {
    MATCH_THREE(3, "3개 일치 (5,000원)", 5000.0),
    MATCH_FOUR(4, "4개 일치 (50,000원)", 50000.0),
    MATCH_FIVE(5, "5개 일치 (1,500,000원)", 1500000.0),
    MATCH_FIVE_AND_BONUS(7, "5개 일치, 보너스 볼 일치 (30,000,000원)", 30000000.0),
    MATCH_SIX(6, "6개 일치 (2,000,000,000원)", 2000000000.0);

    private String contents;
    private int caseNumber;
    private double reward;

    private static HashMap<Integer, WinningCase> winningCaseByIdx = new HashMap<>();

    static {
        Arrays.stream(values()).forEach(e -> winningCaseByIdx.put(e.getCaseNumber(), e));
    }

    private WinningCase(int caseNumber, String contents, double reward) {
        this.caseNumber = caseNumber;
        this.contents = contents;
        this.reward = reward;
    }

    public static WinningCase getByCaseNumber(int caseNumber) {
        return winningCaseByIdx.get(caseNumber);
    }

    public String getContents() {
        return this.contents;
    }

    public int getCaseNumber() {
        return this.caseNumber;
    }

    public double getReward() {
        return this.reward;
    }

}

