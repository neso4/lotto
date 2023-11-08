package lotto;

public enum Rank {
    THREE_MATCH(3,false, "5,000"),
    FOUR_MATCH(4,false,"50,000"),
    FIVE_MATCH(5,false,"1,500,000"),
    FIVE_MATCH_BONUS(5,true,"30,000,000"),
    SIX_MATCH(6,false,"2,000,000,000");



    private final int numberOfMatch;
    private final boolean bonus;
    private final String prizeMoney;

    Rank(int numberOfMatch,boolean bonus,String prizeMoney){
        this.numberOfMatch = numberOfMatch;
        this.bonus = bonus;
        this.prizeMoney = prizeMoney;
    }

    public int getNumberOfMatch() {
        return numberOfMatch;
    }

    public boolean getBonus() {
        return bonus;
    }

    public String getPrizeMoney() {
        return prizeMoney;
    }

    public static boolean isFiveMatchBonusRank(Rank rank) {
        return rank.getNumberOfMatch() == Rank.FIVE_MATCH_BONUS.getNumberOfMatch()
                && rank.getBonus() == Rank.FIVE_MATCH_BONUS.getBonus();
    }
}
