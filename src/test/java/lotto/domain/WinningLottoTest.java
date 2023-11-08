package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import lotto.common.Constant;
import lotto.util.LottoNumberValidator;
import net.bytebuddy.asm.Advice.Argument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningLottoTest {

    @DisplayName("당첨 로또 번호 객체 생성 테스트")
    @ParameterizedTest
    @MethodSource("getLottoNumbersAndBonusNumber")
    void createWinningLotto(List<Integer> numbers, Integer bonusNumber) {
        // Arrange & Act
        WinningLotto winningLotto = WinningLotto.of(numbers, bonusNumber);

        // Assert
        Assertions.assertEquals(Constant.LOTTO_NUM_SIZE, winningLotto.getNumbers().size());
        winningLotto.getNumbers().forEach(number -> Assertions.assertTrue(LottoNumberValidator.validate(number)));
        Assertions.assertTrue(LottoNumberValidator.validate(bonusNumber));
    }

    private static Stream<Arguments> getLottoNumbersAndBonusNumber() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 7),
                Arguments.of(List.of(10, 12, 14, 19, 22, 44), 45)
        );
    }



    @DisplayName("당첨 로또 번호와 보너스 번호가 일치하는 경우 IllegalArgumentException 에러 발생")
    @ParameterizedTest
    @MethodSource("getLottoNumbersAndDuplicationBonusNumber")
    void checkWinningLottoAndDuplicationBonusNumber(List<Integer> numbers, Integer bonusNumber) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> WinningLotto.of(numbers, bonusNumber));
    }

    private static Stream<Arguments> getLottoNumbersAndDuplicationBonusNumber() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 6),
                Arguments.of(List.of(10, 12, 14, 19, 22, 45), 45)
        );
    }

    @DisplayName("당첨 로또 번호가 일치하는지 테스트")
    @ParameterizedTest
    @MethodSource("getLottoNumbersAndExpectedNumber")
    void checkWinningNumber(List<Integer> numbers, Integer bonusNumber, Integer expectedNumber) {
        // Arrange
        WinningLotto winningLotto = WinningLotto.of(numbers, bonusNumber);

        // Act & Assert
        Assertions.assertTrue(winningLotto.isWinningNumber(expectedNumber));
    }

    private static Stream<Arguments> getLottoNumbersAndExpectedNumber() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 7, 6),
                Arguments.of(List.of(10, 12, 14, 19, 22, 44), 7, 44)
        );
    }
}