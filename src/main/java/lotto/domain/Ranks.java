package lotto.domain;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Ranks {
    private final List<Rank> ranks;

    public Ranks(List<Rank> ranks) {
        this.ranks = ranks.stream()
                .sorted(Comparator.comparing(Rank::getPriority).reversed())
                .filter(rank -> !rank.equals(Rank.LAST))
                .toList();
    }

    public List<Rank> getRanks() {
        return ranks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ranks ranks1 = (Ranks) o;
        return Objects.equals(ranks, ranks1.ranks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ranks);
    }
}
