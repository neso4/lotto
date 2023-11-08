package lotto;

public enum Rank {
    FIFTH(3,5_000),
    FOURTH(4,50_000),
    THIRD(5,1_500_000),
    SECOND(5,30_000_000),
    FIRST(6,2_000_000_000),
    MISS(0,0);

    private static final int WINNING_MIN_NUMBER = 3;
    private static final int CLASSIFICATION_SECOND_THIRD_NUMBER = 5;

    private final int numberOfMatch;
    private final int winningMoney;

    Rank(int numberOfMatch,int winningMoney){
        this.numberOfMatch = numberOfMatch;
        this.winningMoney = winningMoney;
    }

    public int getNumberOfMatch() {
        return numberOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public static Rank getRank(int numberOfMatch,boolean matchBounus){
        if(numberOfMatch < WINNING_MIN_NUMBER){
            return MISS;
        }

        if((numberOfMatch==CLASSIFICATION_SECOND_THIRD_NUMBER)&& !matchBounus){
            return SECOND;
        }

        for(Rank rank : values()){
            if(rank.numberOfMatch == numberOfMatch){
                return rank;
            }
        }

        throw new IllegalArgumentException("[ERROR] 맞힌 숫자 개수가 유효한 값이 아닙니다.");
    }


}
