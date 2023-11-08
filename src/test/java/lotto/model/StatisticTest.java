package lotto.model;

import static lotto.model.LottoRank.FIFTH_RANK;
import static lotto.model.LottoRank.FIRST_RANK;
import static lotto.model.LottoRank.FOURTH_RANK;
import static lotto.model.LottoRank.SECOND_RANK;
import static lotto.model.LottoRank.THIRD_RANK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatisticTest {

    @DisplayName("일치하는 개수와 보너스 번호의 포함 여부에 결과를 리스트로 받아 통계를 생성한다.")
    @Test
    void statistics_create_test() {
        // given
        List<Integer> matchedCount = List.of(3, 3, 4, 4, 5, 5, 6);
        List<Boolean> containBonusNumber = List.of(false, true, false, true, false, true, true);

        // when
        Statistic statistic = Statistic.createStatistic(matchedCount, containBonusNumber);
        Map<LottoRank, Integer> result = statistic.getResult();

        // then
        assertEquals(2, result.get(FIFTH_RANK));
        assertEquals(2, result.get(FOURTH_RANK));
        assertEquals(1, result.get(THIRD_RANK));
        assertEquals(1, result.get(SECOND_RANK));
        assertEquals(1, result.get(FIRST_RANK));
    }
}
