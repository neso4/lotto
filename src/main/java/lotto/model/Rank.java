package lotto.model;

public enum Rank {
    FIND(1, 0),
    THIRD(3, 5000),
    FOURTH(4, 50000),
    FIFTH(5, 1500000),
    FIFTH_WITH_BONUS(7, 30000000),
    SIXTH(6, 2000000000);

    private final int label;
    private final int price;

    Rank(int label, int price) {
        this.label = label;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public Rank valueOf(int label) {
        for (Rank rank : Rank.values()) {
            if (label == rank.label) {
                return rank;
            }
        }
        return null;
    }
}
