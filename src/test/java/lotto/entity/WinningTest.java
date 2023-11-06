package lotto.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class WinningTest {

    public static final String errorPrefix="[ERROR]";

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,,4,5,6","1,2,3,4,5,6,","1,2,3,4,5","1,2,3,사,5,6","","1,2,3, 4,5,6","1,2,3,4,5,46"})
    void Winning_생성_WinningNumbers_예외_테스트(String winningNumbers){
        //given
        assertThatThrownBy(()->{
                new Winning(winningNumbers);
            }
                ).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(errorPrefix);
    }

    @Test
    void Winning_생성_유효한_값_테스트(){
        //given
        String inputWinningNumbers = "1,2,3,4,5,6";
        List<Integer> targetNumbers = List.of(1, 2, 3, 4, 5, 6);
        //when
        Winning winning = new Winning(inputWinningNumbers);
        List<Integer> winningNumbers = winning.getWinningNumbers();

        //then
        assertThat(winningNumbers).isEqualTo(targetNumbers);
    }

    @Test
    void Winning_생성_반환_값_리스트_추가_에러_테스트(){
        //given
        String inputWinningNumber = "1,2,3,4,5,6";
        //when
        Winning winning = new Winning(inputWinningNumber);
        List<Integer> winningNumbers = winning.getWinningNumbers();
        //then
        assertThatThrownBy(()->{
                winningNumbers.add(8);
            }
                ).isInstanceOf(UnsupportedOperationException.class);
    }
}
