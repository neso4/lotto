package lotto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.utils.Converter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @Test
    void 중복_예외_테스트(){
        String numbers = "1,2,3,4,5,6";
        Lotto lotto = Converter.stringToLotto(numbers);
        int bonusNumber = 5;

        Assertions.assertThatThrownBy(()->new WinningNumbers(lotto,bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }


    @Test
    void 번호_범위_예외_테스트(){
        String numbers = "1,2,3,4,5,6";
        Lotto lotto = Converter.stringToLotto(numbers);
        int bonusNumber = -1;

        Assertions.assertThatThrownBy(()->new WinningNumbers(lotto,bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1부터 45");
    }

}