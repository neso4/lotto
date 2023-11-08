package lotto.domain;

import java.text.NumberFormat;

public enum LottoGrade {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    MISS(0, false, 0),
    ;

    private final int matchCount;
    private final boolean matchBonus;
    private final int winningMoney;

    LottoGrade(int matchCount, boolean matchBonus, int winningMoney) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.winningMoney = winningMoney;
    }

    public static LottoGrade of(int matchCount, boolean matchBonus) {
        for (LottoGrade value : values()) {
            if (isMatchCountWorthy(value, matchCount) && isMatchBonusWorthy(value, matchBonus)) {
                return value;
            }
        }

        return MISS;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public String getMatchLabel() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.matchCount).append("개 일치");

        if(this.matchBonus) {
            sb.append(", 보너스 볼 일치");
        }

        String commarizedWinningMoney = NumberFormat.getInstance().format(this.winningMoney);
        sb.append(" (").append(commarizedWinningMoney).append("원)");

        return sb.toString();
    }

    private static boolean isMatchCountWorthy(LottoGrade grade, int matchCount) {
        return matchCount >= grade.matchCount;
    }

    private static boolean isMatchBonusWorthy(LottoGrade grade, boolean matchBonus) {
        if (!grade.matchBonus) {
            return true;
        }

        return matchBonus;
    }
}
