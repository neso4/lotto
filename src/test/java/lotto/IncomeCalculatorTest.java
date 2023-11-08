package lotto;

import calculator.IncomeCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

class IncomeCalculatorTest {
    private Lottos lottos;
    private StandardLotto standardLotto;
    @BeforeEach
    void init() {
        Lotto userLotto = new Lotto(List.of(1,2,3,4,5,6));
        int bonusNumber = 7;
        standardLotto = new StandardLotto(userLotto, bonusNumber);
        Lotto firstPrizeLotto = new Lotto(List.of(1,2,3,4,5,6));
        Lotto noneLotto = new Lotto(List.of(8,9,10,11,12,13));
        lottos = new Lottos(List.of(firstPrizeLotto, noneLotto));
    }

    @Test
    @DisplayName("Lottos 컬렉션이 비교 결과를 잘 전달하고, WinningCalc 는 해당 결과를 잘 가지고 있음을 테스트")
    void calculateWinning() {
        IncomeCalculator incomeCalculator = new IncomeCalculator();
        lottos.calculateWinning(standardLotto, incomeCalculator);
        Map<Prize, Integer> prizeCountMap = incomeCalculator.getPrizeCountMap();
        assertThat(prizeCountMap.get(Prize.FIRST)).isOne();
        assertThat(prizeCountMap.get(Prize.NONE)).isOne();
    }

    @Test
    @DisplayName("총 상금의 계산을 테스트")
    void getTotalWinning() {
        IncomeCalculator incomeCalculator = new IncomeCalculator();
        incomeCalculator.clear();
        incomeCalculator.addCount(Prize.FIRST);
        incomeCalculator.addCount(Prize.FIRST);

        long totalIncome = incomeCalculator.getTotalIncome();
        assertThat(totalIncome).isEqualTo(Prize.FIRST.getWinning() * 2L);
    }
}