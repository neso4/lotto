package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @DisplayName("숫자의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 숫자는 6개가 되어야 합니다.");
    }

    @DisplayName("1과 45 사이의 숫자가 아닐때 예외가 발생한다.")
    @Test
    void createNumberByOutOfRange() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 80)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 1과 45 사이의 숫자만 가능합니다.");
    }

    @DisplayName("숫자에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 중복된 숫자가 존재합니다.");
    }

    @DisplayName("숫자들을 오름차순으로 만들어 준다.")
    @Test
    void createAscendingNumber() {
        List<Integer> numbers = new ArrayList<>(List.of(2, 3, 8, 12, 41, 28));
        Lotto lotto = new Lotto(numbers);
        assertThat(lotto.getNumbers()).isSorted();
    }

    @DisplayName("숫자들을 전달한다.")
    @Test
    void createPassingNumbers() {
        assertThat(new Lotto(List.of(2, 24, 32, 29, 45, 17)).getNumbers()).isInstanceOf(List.class);
    }

    @DisplayName("숫자들을 문자열로 전달한다.")
    @Test
    void createStringOfNumbers() {
        assertThat(new Lotto(List.of(2, 24, 32, 29, 45, 17)).toString())
                .startsWith("[")
                .contains("2, 17, 24, 29, 32, 45")
                .endsWith("]");
    }
}