package model;

import java.util.List;

public class WinLotto extends Lotto {
    private final Integer bonus;

    public WinLotto(List<Integer> numbers, Integer bonus) {
        super(numbers);
        this.bonus = bonus;
    }

    public List<Integer> getNumbers() {
        return super.getNumbers();
    }

    public Integer getBonus() {
        return bonus;
    }
}
