package lotto.model;

import lotto.view.OutputViewImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    @DisplayName("당첨 내역 출력 테스트")
    @Test
    void checkResultDto() {
        //given
        List<Rank> ranks = List.of(Rank.FIFTH, Rank.THIRD);
        Result result = Result.from(ranks);
        //when
        StringBuilder stringBuilder = new StringBuilder();
        result.forEachOrdered(rank ->
                stringBuilder.append(convertWinningStatisticFormat(result, rank)));
        //then
        assertThat(stringBuilder.toString())
                .contains(
                        "3개 일치 (5,000원) - 1개",
                        "4개 일치 (50,000원) - 0개",
                        "5개 일치 (1,500,000원) - 1개",
                        "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                        "6개 일치 (2,000,000,000원) - 0개"
                );
    }

    String convertWinningStatisticFormat(Result result, Rank rank) {
        return String.format(OutputViewImpl.PRIZE_MESSAGE_FORMAT, rank.getMessage(),
                convertPrizeFormat(rank.getPrize()),
                result.getResult(rank));
    }

    String convertPrizeFormat(Integer prize) {
        return new DecimalFormat(OutputViewImpl.PRIZE_NUMBER_FORMAT).format(prize);
    }
}
