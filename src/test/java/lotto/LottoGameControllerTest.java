package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import lotto.preprocessor.BonusNumPreprocessor;
import lotto.preprocessor.PurchasePreprocessor;
import lotto.preprocessor.WinningNumsPreprocessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGameControllerTest {

    PurchasePreprocessor purchasePreprocessor = new PurchasePreprocessor();
    WinningNumsPreprocessor winningNumsPreprocessor = new WinningNumsPreprocessor();
    BonusNumPreprocessor bonusNumPreprocessor = new BonusNumPreprocessor();

    @DisplayName("로또 구매 금액이 조건에 맞지 않으면 true를 반환한다.")
    @Test
    void isInvalidPurchaseTest() {
        assertThat(purchasePreprocessor.isInvalid("1234")).isTrue();
        assertThat(purchasePreprocessor.isInvalid("-1234")).isTrue();
        assertThat(purchasePreprocessor.isInvalid("0")).isTrue();

        assertThat(purchasePreprocessor.isInvalid("1000")).isFalse();
    }

    @DisplayName("로또 당첨 번호가 숫자가 아니면 true를 반환한다.")
    @Test
    void allWinningNumsAreNumberTest() {
        assertThat(winningNumsPreprocessor.isInvalid("a")).isTrue();
        assertThat(winningNumsPreprocessor.isInvalid("a, b, c")).isTrue();
    }

    @DisplayName("로또 당첨 번호가 6개의 숫자가 아니면 true를 반환한다.")
    @Test
    void isInvalidWinningNumsSizeTest() {
        // size test
        assertThat(winningNumsPreprocessor.isInvalid("1,2,3,4,5")).isTrue();
        assertThat(winningNumsPreprocessor.isInvalid("1,2,3,4,5,6,7")).isTrue();
    }

    @DisplayName("로또 당첨 번호가 1~45사이의 숫자가 아니면 true를 반환한다.")
    @Test
    void isInvalidWinningNumsRangeTest() {
        // range test
        assertThat(winningNumsPreprocessor.isInvalid("0,2,3,4,5,5")).isTrue();
        assertThat(winningNumsPreprocessor.isInvalid("1,2,3,4,5,46")).isTrue();
    }

    @DisplayName("로또 당첨 번호 중 중복이 있으면 true를 반환한다.")
    @Test
    void isDuplicatedWinningNumsTest() {
        // duplication test
        assertThat(winningNumsPreprocessor.isInvalid("1,2,3,4,5,5")).isTrue();
    }

    @DisplayName("로또 당첨 번호가 유효하다면 flase를 반환한다.")
    @Test
    void isValidWinningNumsTest() {
        // 정상 입력
        assertThat(winningNumsPreprocessor.isInvalid("1,2,3,4,5,6")).isFalse();
        assertThat(winningNumsPreprocessor.isInvalid("1, 2, 3, 4, 5, 6")).isFalse();
    }

    @DisplayName("보너스 번호가 유효하지 않다면 true를 반환한다.")
    @Test
    void isInvalidBonusNumsTest() {
        // init
        bonusNumPreprocessor.setWinningNumbers(List.of(1, 2, 3, 4, 5, 6));

        // 중복 테스트
        assertThat(bonusNumPreprocessor.isInvalid("1")).isTrue();
        assertThat(bonusNumPreprocessor.isInvalid("6")).isTrue();

        // 숫자 테스트
        assertThat(bonusNumPreprocessor.isInvalid("1, 2")).isTrue();

        // 범위 테스트
        assertThat(bonusNumPreprocessor.isInvalid("0")).isTrue();
        assertThat(bonusNumPreprocessor.isInvalid("46")).isTrue();

        // 정상 입력
        assertThat(bonusNumPreprocessor.isInvalid("7")).isFalse();
    }
}