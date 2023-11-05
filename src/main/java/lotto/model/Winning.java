package lotto.model;

import static lotto.constants.Error.DUPLICATE_INVALID;

public class Winning {
    private final Lotto lotto;
    private final int bonus;

    private Winning(Lotto lotto, int bonus) {
        validate(lotto, bonus);
        this.lotto = lotto;
        this.bonus = bonus;
    }

    public static Winning of(Lotto lotto, int bonus) {
        return new Winning(lotto, bonus);
    }

    private static void validate(Lotto lotto, int bonus) {
        if (lotto.isMatchNumber(bonus)) {
            throw new IllegalArgumentException(DUPLICATE_INVALID.getMessage());
        }
    }

    public Lotto getLotto() {
        return lotto;
    }

    public int getBonus() {
        return bonus;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Prize\n");
        sb.append("로또 당첨 번호: ").append(lotto).append("\n");
        sb.append("보너스 번호: ").append(bonus).append("\n");
        return sb.toString();
    }
}
