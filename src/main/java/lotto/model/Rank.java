package lotto.model;

public enum Rank {
    FIFTH(5, 3, 5000, "3개 일치 (5,000원) - "),
    FOURTH(4, 4, 50000, "4개 일치 (50,000원) - "),
    THIRD(3, 5, 1500000, "5개 일치 (1,500,000원) - "),
    SECOND(2, 5, 30000000, "5개 일치, 보너스 볼 일치 (30,000,000원) - "),
    FIRST(1, 6, 2000000000, "6개 일치 (2,000,000,000원) - ");

    private final int ranking;
    private final int correctNumber;
    private final int prize;
    private final String resultPrint;

    Rank(int ranking, int correctNumber, int prize, String resultPrint) {
        this.ranking = ranking;
        this.correctNumber = correctNumber;
        this.prize = prize;
        this.resultPrint = resultPrint;
    }

    public int getRanking() {
        return ranking;
    }

    public int getCorrectNumber() {
        return correctNumber;
    }

    public int getPrize() {
        return prize;
    }

    public String getResultPrint() {
        return resultPrint;
    }

    public static Rank getRank(int num) {
        for (Rank value : values()) {
            if (value.correctNumber == num) {
                return value;
            }
        }
        return null;
    }
}
