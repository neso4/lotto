package lotto.domain;

import lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningResultTest {

    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4,5,6:FIRST", "1,2,3,4,5,7:SECOND", "1,2,3,4,5,8:THIRD", "1,2,3,4,8,10:FOURTH",
                "1,2,3,8,9,10:FIFTH", "1,2,8,9,10,11:MISS"}, delimiter = ':')
    @DisplayName("사용자가 구매한 로또 번호와 당첨 번호를 비교해 등수 확인")
    void compare(String input, Ranking ranking){
        String[] list = input.split(",");
        List<Integer> tickerNumberList = new ArrayList<>();
        for (String s : list) {
            tickerNumberList.add(Integer.parseInt(s));
        }
        Lotto lottoList = new Lotto(tickerNumberList);
        Lotto lotto = new Lotto(Arrays.asList(1,2,3,4,5,6));
        int bonusball = 7;
        WinningResult winningResult = new WinningResult(lotto, bonusball);

        assertThat(winningResult.match(lottoList)).isEqualTo(ranking);
    }
}