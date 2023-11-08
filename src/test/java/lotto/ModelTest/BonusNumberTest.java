package lotto.ModelTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import lotto.Model.BonusNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusNumberTest {
    private BonusNumber bonusNumber;
    private List<Integer> winningNumbers;

    @BeforeEach
    public void setUp() {
        winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    }
    @DisplayName("당첨번호와 중복된 번호를 입력하면 예외가 발생한다. ")
    @Test
    public void testIllegalArgumentExceptionWithBonus6() {
        // Arrange
        int bonus = 6;
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new BonusNumber(bonus, winningNumbers));
        // Assert
        assertTrue(exception.getMessage().contains("[ERROR]"));
    }

    @DisplayName("범위를 벗어난 번호를 입력하면 예외가 발생한다. ")
    @Test
    public void testIllegalArgumentExceptionWithBonus47() {
        // Arrange
        int bonus = 47;
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new BonusNumber(bonus, winningNumbers));
        // Act & Assert
        assertTrue(exception.getMessage().contains("[ERROR]"));
    }



}