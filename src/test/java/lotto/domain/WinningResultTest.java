package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningResultTest {
    @ParameterizedTest
    @CsvSource(value = {"1,2,10,11,12,13:MISS", "1,2,3,4,5,6:FIRST", "1,2,3,4,5,7:SECOND", "1,2,3,4,5,8:THIRD",
            "1,2,3,4,8,10:FOURTH",
            "1,2,3,8,9,10:FIFTH"}, delimiter = ':')
    @DisplayName("로또 번호 등수 테스트")
    public void compare(String input, Ranking ranking) {
        String[] list = input.split(",");
        List<Integer> ticketNumberList = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            ticketNumberList.add(Integer.parseInt(list[i]));
        }
        Lotto lottoList = new Lotto(ticketNumberList);
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusball = 7;
        WinningResult winningResult = new WinningResult(lotto, bonusball);

        assertThat(winningResult.match(lottoList)).isEqualTo(ranking);
    }

}