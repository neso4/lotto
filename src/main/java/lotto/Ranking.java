package lotto;

public enum Ranking {
    FIRST(3, 5_000, "3개 일치 (5,000원)"),
    SECOND(4, 50_000, "4개 일치 (50,000원)"),
    THIRD(5, 1_500_000, "5개 일치 (1,500,000원)"),
    FOURTH(5, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    FIFTH(6, 2_000_000_000, "6개 일치 (2,000,000,000원)"),
    NONE(0, 0, "");

    private final int matchCount;
    private final int prizeMoney;
    private final String Message;

    Ranking(int matchCount, int prizeMoney, String Message) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.Message = Message;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public String getMessage() {
        return Message;
    }

    public static Ranking getRank(int matchCount, boolean matchBonus) {
        if (matchCount == 6) {
            return FIRST;
        } else if (matchCount == 5 && matchBonus) {
            return SECOND;
        } else if (matchCount == 5) {
            return THIRD;
        } else {
            for (Ranking ranking : Ranking.values()) {
                if (ranking.getMatchCount() == matchCount && ranking != SECOND && ranking != THIRD) {
                    return ranking;
                }
            }
        }
        return NONE;
    }
}
