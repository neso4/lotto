package lotto.model;

import java.util.List;
import java.util.Map;

public class WiningNumbers {

    private final Lotto numbers;
    private final Bonus bonus;

    public WiningNumbers(final Lotto numbers, final Bonus bonus) {
        this.numbers = numbers;
        this.bonus = bonus;
    }

    public WiningTypeCount countWining(List<Lotto> lottos) {
        Map<WiningType, Integer> winingCountMap = WiningType.getWiningCountEmptyMap();
        lottos.forEach(lotto -> {
            int correctCount = lotto.countCorrectNumbers(numbers);
            boolean hasBonus = bonus.compareNumber(lotto);
            WiningType wining = WiningType.findByWiningType(correctCount, hasBonus);
            winingCountMap.computeIfPresent(wining, (key, count) -> ++count);
        });
        return new WiningTypeCount(winingCountMap);
    }
}
