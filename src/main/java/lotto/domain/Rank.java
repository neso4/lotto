package lotto.domain;

public enum Rank {
	FIRST(1, 6, false, 2_000_000_000),
	SECOND(2, 5, true, 30_000_000),
	THIRD(3, 5, false, 1_500_000),
	FOURTH(4, 4, false, 50_000),
	FIFTH(5, 3, false, 5_000),
	MISS(6, 0, false, 0);

	private final int rank;
	private final int matchNormalCount;
	private final boolean needMatchBonus;
	private final int reward;

	Rank(int rank, int matchNormalCount, boolean needMatchBonus, int reward) {
		this.rank = rank;
		this.matchNormalCount = matchNormalCount;
		this.needMatchBonus = needMatchBonus;
		this.reward = reward;
	}

	public int getReward() {
		return reward;
	}
}
