package lotto.model;

import static lotto.util.ExceptionMessage.BONUS_ALREADY_CONTAIN_WINNING;
import static lotto.util.ExceptionMessage.COMMON_INVALID_TYPE;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BonusTest {
    List<Integer> winningNumbers;

    @BeforeEach
    void setUp() {
        winningNumbers = new ArrayList<>(List.of(1,2,3,4,5,6));
    }

    @Test
    void 보너스번호가_당첨번호와_중복되면_생성되지_않는다() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
            () -> new Bonus(winningNumbers, "1"));
        assertEquals(e.getMessage(), BONUS_ALREADY_CONTAIN_WINNING.getMessage());
    }

    @Test
    void 보너스번호가_숫자_이외의_문자라면_생성되지_않는다() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
            () -> new Bonus(winningNumbers, "as"));
        assertEquals(e.getMessage(), COMMON_INVALID_TYPE.getMessage());
    }

    @Test
    void 보너스번호가_정상적으로_만들어진다() {
        Bonus bonus = new Bonus(winningNumbers, "7");
        assertEquals(bonus.getNumber(), 7);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "58,[ERROR] 1에서 45사이의 숫자로 입력해주세요.",
        "-5,[ERROR] 1에서 45사이의 숫자로 입력해주세요.",
    })
    void 보너스번호가_1과_45사이의_숫자라면_생성되지_않는다(String bonusNumber, String exceptionMessage) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
            () -> new Bonus(winningNumbers, bonusNumber));
        assertEquals(e.getMessage(), exceptionMessage);
    }

}