package lotto.domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static lotto.domain.Grade.FIVE;
import static lotto.domain.Grade.FIVE_AND_BONUS;
import static lotto.domain.Grade.FOUR;
import static lotto.domain.Grade.NONE;
import static lotto.domain.Grade.SIX;
import static lotto.domain.Grade.THREE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    private static final WinningNumbers ANSWER = WinningNumbers.of("1,2,3,4,5,6");
    private static final BonusNumber BONUS = BonusNumber.from("45", ANSWER);
    private static final String PRICE = "1000";
    private Lottos lottos;

    @DisplayName("정답과 보너스 점수에 따라 로또들을 채점해서 결과 NONE을 Map에 담을 수 있다")
    @Test
    void lottosGradeNoneTest() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    lottos = Lottos.of(PurchaseAmount.of(PRICE));
                    Map<Grade, Integer> grade = lottos.grade(ANSWER, BONUS);
                    assertThat(grade.get(NONE)).isEqualTo(1);
                },
                List.of(1, 7, 8, 9, 10, 11)
        );
    }

    @DisplayName("정답과 보너스 점수에 따라 로또들을 채점해서 결과 THREE을 Map에 담을 수 있다")
    @Test
    void lottosGradeThreeTest() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    lottos = Lottos.of(PurchaseAmount.of(PRICE));
                    Map<Grade, Integer> grade = lottos.grade(ANSWER, BONUS);
                    assertThat(grade.get(THREE)).isEqualTo(1);
                },
                List.of(1, 2, 3, 9, 10, 11)
        );
    }

    @DisplayName("정답과 보너스 점수에 따라 로또들을 채점해서 결과 FOUR을 Map에 담을 수 있다")
    @Test
    void lottosGradeFourTest() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    lottos = Lottos.of(PurchaseAmount.of(PRICE));
                    Map<Grade, Integer> grade = lottos.grade(ANSWER, BONUS);
                    assertThat(grade.get(FOUR)).isEqualTo(1);
                },
                List.of(1, 2, 3, 4, 10, 11)
        );
    }

    @DisplayName("정답과 보너스 점수에 따라 로또들을 채점해서 결과 FIVE를 Map에 담을 수 있다")
    @Test
    void lottosGradeFiveTest() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    lottos = Lottos.of(PurchaseAmount.of(PRICE));
                    Map<Grade, Integer> grade = lottos.grade(ANSWER, BONUS);
                    assertThat(grade.get(FIVE)).isEqualTo(1);
                },
                List.of(1, 2, 3, 4, 5, 11)
        );
    }

    @DisplayName("정답과 보너스 점수에 따라 로또들을 채점해서 결과 FIVE_AND_BONUS를 Map에 담을 수 있다")
    @Test
    void lottosGradeFiveAndBonusTest() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    lottos = Lottos.of(PurchaseAmount.of(PRICE));
                    Map<Grade, Integer> grade = lottos.grade(ANSWER, BONUS);
                    assertThat(grade.get(FIVE_AND_BONUS)).isEqualTo(1);
                },
                List.of(1, 2, 3, 4, 5, 45)
        );
    }

    @DisplayName("정답과 보너스 점수에 따라 로또들을 채점해서 결과 SIX를 Map에 담을 수 있다")
    @Test
    void lottosGradeSixTest() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    lottos = Lottos.of(PurchaseAmount.of(PRICE));
                    Map<Grade, Integer> grade = lottos.grade(ANSWER, BONUS);
                    assertThat(grade.get(SIX)).isEqualTo(1);
                },
                List.of(1, 2, 3, 4, 5, 6)
        );
    }
}