package lotto.model;

public enum LottoRank {
    /**
     * 로또 당첨 순위: 1등
     */
    RANK_1ST(1, 6.0, 2000000000),
    /**
     * 로또 당첨 순위: 2등
     */
    RANK_2ND(2, 5.5, 30000000),
    /**
     * 로또 당첨 순위: 3등
     */
    RANK_3RD(3, 5.0, 1500000),
    /**
     * 로또 당첨 순위: 4등
     */
    RANK_4TH(4, 4.0, 50000),
    /**
     * 로또 당첨 순위: 5등
     */
    RANK_5TH(5, 3.0, 5000),
    /**
     * 당첨이 없음
     */
    RANK_NO(0, 0.0, 0);

    private final double rank;
    private final double match;
    private final int price;

    LottoRank(int rank, double match, int price) {
        this.rank = rank;
        this.match = match;
        this.price = price;
    }

    public double getRank() {
        return rank;
    }

    public int getPrice() {
        return price;
    }

    public double getMatch() {
        return match;
    }

    /**
     * 정수를 LottoRank로 변환
     *
     * @param match 정수형 랭크 숫자
     * @return LottoRank
     */
    public static LottoRank parseLottoRank(double match) {
        for (LottoRank rank : LottoRank.values()) {
            if (rank.getMatch() == match) {
                return rank;
            }
        }

        return RANK_NO;
    }
}
