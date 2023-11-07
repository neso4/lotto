package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoTest {
    private static final List<Integer> WINNING_NUMBER_INPUT = List.of(1, 7, 13, 17, 28, 34);
    private static final List<Integer> LOTTO_INPUT = List.of(1, 7, 19, 23, 38, 44);
    private static final int WINNING_COUNT = 2;

    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 1~45사이의 값이 아니면 예외가 발생한다.")
    @Test
    void createLottoByOutOfRangeNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 13, 23, 32, 38, 49)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또의 당첨 개수를 반환한다.")
    @Test
    void calculateLotto() {
        Lotto winningNumber = new Lotto(WINNING_NUMBER_INPUT);
        Lotto lotto = new Lotto(LOTTO_INPUT);

        assertEquals(lotto.countMatchingLottoNumbers(winningNumber), WINNING_COUNT);
    }
}