package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 Exception 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개로 이루어져야 합니다.");
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 Exception 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호가 중복되어서는 안됩니다.");
    }

    @DisplayName("생성된 로또 객체의 로또 번호가 오름차순으로 정렬된다.")
    @Test
    void createLotto() {
        // given
        List<Integer> numbers = Arrays.asList(3, 4, 1, 6, 2, 5);

        // when
        Lotto lotto = new Lotto(numbers);

        // then
        assertThat(lotto.getNumbers()).isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("로또 번호가 1 보다 작은 숫자인 경우 Exception 발생한다. ")
    @Test
    void validateNumberInRange1() {
        // given
        List<Integer> numbers = Arrays.asList(0, 2, 3, 4, 5, 6);

        // when // then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 1 ~ 45 사이의 숫자여야 합니다.");
    }

    @DisplayName("로또 번호가 45 보다 큰 숫자인 경우 Exception 발생한다. ")
    @Test
    void validateNumberInRange2() {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 46);

        // when // then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 1 ~ 45 사이의 숫자여야 합니다.");
    }
}