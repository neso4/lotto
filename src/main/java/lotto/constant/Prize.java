package lotto.constant;

public enum Prize {
    FIRST(6, "(2,000,000,000원)", 2000000000),
    SECOND(7, "(30,000,000원)", 30000000),
    THIRD(5, "(1,500,000원)", 1500000),
    FOURTH(4, "(50,000원)", 50000),
    FIFTH(3, "(5,000원)", 5000),
    NONE(0, "0", 0);

    private final int match;
    private final String panel;
    private final int money;

    Prize(int match, String panel, int money) {
        this.match = match;
        this.panel = panel;
        this.money = money;
    }

    public static Prize getByMatch(int match) {
        for (Prize prize : values()) {
            if (prize.match == match) {
                return prize;
            }
        }
        return Prize.NONE;
    }

    public int getMoney() {
        return money;
    }

    public static String prizeResult(Prize prize) {
        if (prize == SECOND) {
            return "5개 일치, 보너스 볼 일치 " + prize.panel + " - ";
        }
        return prize.match + "개 일치, " + prize.panel + " - ";
    }
}