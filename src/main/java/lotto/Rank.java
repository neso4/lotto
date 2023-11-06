package lotto;

public enum Rank {

    FIRST(6, 2000000000L, "2,000,000,000"),
    SECOND(5, 30000000L, "30,000,000"),
    THIRD(5, 1500000L, "1,500,000"),
    FOURTH(4, 50000L, "50,000"),
    FIFTH(3, 5000L, "5,000");

    private final int count;
    private final long prizeMoney;
    private final String printablePrizeMoney;

    private Rank(int count, long prizeMoney, String printablePrizeMoney) {
        this.count = count;
        this.prizeMoney = prizeMoney;
        this.printablePrizeMoney = printablePrizeMoney;
    }

    public String getMessage(int count) {
        if (this.name().equals(SECOND)) {
            return String.format("%d개 일치, 보너스 볼 일치 (%s) - %d개", this.getCount(), this.getPrintablePrizeMoney(), count);
        }
        return String.format("%d개 일치 (%s) - %d개", this.getCount(), this.getPrintablePrizeMoney(), count);
    }

    public int getCount() {
        return count;
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }

    public String getPrintablePrizeMoney() {
        return printablePrizeMoney;
    }
}
