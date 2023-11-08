package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserInputCheckerTest {
    private UserInputChecker userInputChecker;
    private List<Integer> winningNumbersTest = Arrays.asList(1, 2, 3, 4, 6, 6);

    @BeforeEach
    void sutUp() {
        userInputChecker = new UserInputChecker();
    }

    @Test
    void 구입금액_단위_예외처리() {
        assertThrows(IllegalArgumentException.class, () -> userInputChecker.checkInputUnit(10010));
    }

    @Test
    void 입력값_자료형_예외처리() {
        assertThrows(IllegalArgumentException.class, () -> userInputChecker.checkInt("a"));
    }


}