package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WinningResultTest {
    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4,5,6:FIRST", "1,2,3,4,5,7:SECOND","1,2,3,4,5,8:THIRD",
    "1,2,3,4,8,9:FOURTH","1,2,3,8,9,10:FIFTH","1,2,8,9,10,11:NOTHING"}, delimiter= ':')
    @DisplayName("구매한 로또 번호와 당첨 번호를 비교하여서 등수 확인")
    void compare(String input, Ranking ranking){
        String[] list = input.split(",");
        List<Integer> lottoNumber = new ArrayList<>();
        for(int i=0; i<list.length; i++){
            lottoNumber.add(Integer.parseInt(list[i]));
        }
        Lotto lottoList = new Lotto(lottoNumber);
        Lotto lotto = new Lotto(Arrays.asList(1,2,3,4,5,6));
        int bonusBall = 7;
        WinningResult winningResult = new WinningResult(lotto, bonusBall);
        assertThat(winningResult.match(lottoList)).isEqualTo(ranking);
    }
}
