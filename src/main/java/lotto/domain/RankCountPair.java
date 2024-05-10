package lotto.domain;

import java.util.Objects;

public class RankCountPair {
    private final Rank RANK;
    private final int COUNT;

    public RankCountPair(Rank RANK, int COUNT) {
        this.RANK = RANK;
        this.COUNT = COUNT;
    }

    public Rank getRANK() {
        return RANK;
    }

    public int getCOUNT() {
        return COUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RankCountPair that = (RankCountPair) o;
        return COUNT == that.COUNT && RANK == that.RANK;
    }

    @Override
    public int hashCode() {
        return Objects.hash(RANK, COUNT);
    }
}
