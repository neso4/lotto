package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // 아래에 추가 테스트 작성 가능
    @Test
    @DisplayName("번호가 범위를 벗어날 때 예외 발생")
    void createLottoWithNumbersOutOfRange() {
        List<Integer> numbers = Arrays.asList(0, 2, 3, 4, 5, 49);
        assertThrows(IllegalArgumentException.class, () -> new Lotto(numbers));
    }

    @Test
    @DisplayName("유효한 번호로 로또 생성")
    void createLottoWithValidNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);
        assertNotNull(lotto);
        assertEquals(numbers, lotto.getNumbers());
    }
}