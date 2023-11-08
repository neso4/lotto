package lotto.domain;

public enum LottoPrize {
    CORRECT_3_NUMBERS(5000, "3개 일치 (5,000원)"),
    CORRECT_4_NUMBERS(50000, "4개 일치 (50,000원)"),
    CORRECT_5_NUMBERS(1500000, "5개 일치 (1,500,000원)"),
    CORRECT_5_NUMBERS_WITH_BONUS(30000000, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    CORRECT_6_NUMBERS(2000000000, "6개 일치 (2,000,000,000원)"),
    OTHERS(0, "등외");

    private final int money;
    private final String title;
    private static final String link = " - ";

    LottoPrize(int money, String title) {
        this.money = money;
        this.title = title;
    }

    public static LottoPrize findPrize(int duplicatedCount, boolean matchBonus) {
        if(duplicatedCount < 3) return OTHERS;
        if(duplicatedCount == 3) return CORRECT_3_NUMBERS;
        if(duplicatedCount == 4) return CORRECT_4_NUMBERS;
        if(duplicatedCount == 5){
            if(matchBonus) return CORRECT_5_NUMBERS_WITH_BONUS;
            return CORRECT_5_NUMBERS;
        }
        return CORRECT_6_NUMBERS;
    }

    public int getMoney() {
        return money;
    }

    public String getTitle() {
        return title + link;
    }

}
