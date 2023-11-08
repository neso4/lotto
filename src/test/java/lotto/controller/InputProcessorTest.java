package lotto.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputProcessorTest {

    @DisplayName("로또 구매 입력 처리 테스트")
    @Test
    void processPurchaseAmountInputTest() {
        // when
        int purchaseAmount = InputProcessor.processPurchaseAmountInput("8000");
        // then
        assertThat(purchaseAmount).isEqualTo(8000);
    }

    @DisplayName("로또 구매 입력 처리 에러 처리 테스트")
    @Test
    void processPurchaseAmountInputErrorTest() {
        // 1000원 단위 검사
        assertThrows(IllegalArgumentException.class,
                () -> InputProcessor.processPurchaseAmountInput("700"));
        assertThrows(IllegalArgumentException.class,
                () -> InputProcessor.processPurchaseAmountInput("12700"));
        // 숫자 여부 검사
        assertThrows(IllegalArgumentException.class,
                () -> InputProcessor.processPurchaseAmountInput("안"));
        // 공백 검사
        assertThrows(IllegalArgumentException.class,
                () -> InputProcessor.processPurchaseAmountInput(""));
    }

    @DisplayName("로또 당첨 번호 입력 처리 테스트")
    @Test
    void processWinningNumbersInputTest() {
        // when
        List<Integer> winningNumbers = InputProcessor.processWinningNumbersInput("1,2,3,4,5,6");
        // then
        assertThat(winningNumbers).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("로또 당첨 번호 입력 에러 처리 테스트")
    @Test
    void processWinningNumbersInputErrorTest() {
        // 6개 미만 검사
        assertThrows(IllegalArgumentException.class,
                () -> InputProcessor.processWinningNumbersInput("1"));
        // , 뒤 공백 검사
        assertThrows(IllegalArgumentException.class,
                () -> InputProcessor.processWinningNumbersInput("1,"));
        // 중복 검사
        assertThrows(IllegalArgumentException.class,
                () -> InputProcessor.processWinningNumbersInput("1,2,3,4,5,5"));
        // 6개 초과 검사
        assertThrows(IllegalArgumentException.class,
                () -> InputProcessor.processWinningNumbersInput("1,2,3,4,5,6,7"));
        // 범위 미만 검사
        assertThrows(IllegalArgumentException.class,
                () -> InputProcessor.processWinningNumbersInput("1,2,3,4,5,0"));
        // 범위 초과 검사
        assertThrows(IllegalArgumentException.class,
                () -> InputProcessor.processWinningNumbersInput("1,2,3,4,5,46"));
        // 숫자 여부 검사
        assertThrows(IllegalArgumentException.class,
                () -> InputProcessor.processWinningNumbersInput("안,녕"));
        // 공백 검사
        assertThrows(IllegalArgumentException.class,
                () -> InputProcessor.processWinningNumbersInput(""));
    }

    @DisplayName("로또 보너스 번호 입력 처리 테스트")
    @Test
    void processBonusNumberInputTest() {
        // given
        List<Integer> winningNumbers = new ArrayList<>(List.of(1, 5, 10, 15, 20, 25));
        // when
        int bonusNumber = InputProcessor.processBonusNumberInput("7", winningNumbers);
        // then
        assertThat(bonusNumber).isEqualTo(7);
    }

    @DisplayName("로또 보너스 번호 입력 에러 처리 테스트")
    @Test
    void processBonusNumberInputErrorTest() {
        // given
        List<Integer> winningNumbers = new ArrayList<>(List.of(1, 5, 10, 15, 20, 25));
        // then
        // 숫자 여부 검사
        assertThrows(IllegalArgumentException.class,
                () -> InputProcessor.processBonusNumberInput("안", winningNumbers));
        // 공백 검사
        assertThrows(IllegalArgumentException.class,
                () -> InputProcessor.processBonusNumberInput("", winningNumbers));
        // , 뒤 공백 검사
        assertThrows(IllegalArgumentException.class,
                () -> InputProcessor.processBonusNumberInput("3,", winningNumbers));
        // 중복 검사
        assertThrows(IllegalArgumentException.class,
                () -> InputProcessor.processBonusNumberInput("1", winningNumbers));
        // 범위 미만 검사
        assertThrows(IllegalArgumentException.class,
                () -> InputProcessor.processBonusNumberInput("0", winningNumbers));
        // 범위 초과 검사
        assertThrows(IllegalArgumentException.class,
                () -> InputProcessor.processBonusNumberInput("46", winningNumbers));
    }

}