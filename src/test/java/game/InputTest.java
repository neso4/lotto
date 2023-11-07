package game;

import lotto.exception.InputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputTest {
    InputValidator inputValidator = new InputValidator();
    List<Integer> numbers = new ArrayList<>();
    @BeforeEach
    void set() {
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
    }
    @DisplayName("숫자 6개가 아닐 시 오류가 발생한다")
    @Test
    void numberByOverSize(){
        numbers.add(6);
        numbers.add(7);

        assertThrows(IllegalArgumentException.class, () -> inputValidator.checkWinningNumbers(numbers));
    }

    @DisplayName("중복된 숫자가 있을 시 오류가 발생한다")
    @Test
    void numberDuplicate(){
        numbers.add(5);
        assertThrows(IllegalArgumentException.class, () -> inputValidator.checkWinningNumbers(numbers));
    }

    @DisplayName("1~45 범위 내 숫자가 아닐 시 오류가 발생한다.")
    @Test
    void outOfRangeNumber(){
        numbers.add(46);
        assertThrows(IllegalArgumentException.class, () -> inputValidator.checkWinningNumbers(numbers));
    }
}
