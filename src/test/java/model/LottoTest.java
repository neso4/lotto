package model;

import model.Lotto;
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
    public void Lotto_생성_테스트(){
        // given
        List<Integer> numbers = Arrays.asList(8, 19, 21, 30, 33, 42);
        // when
        Lotto lotto = new Lotto(numbers);
        // then
        assertNotNull(lotto);
        assertEquals(numbers, lotto.getNumbers());
    }

    @Test
    public void Lotte_실패_테스트_넘버_사이즈_불일치(){
        // given
        List<Integer> numbers = Arrays.asList(8, 19, 21, 30, 33);
        // when & then
        assertThrows(IllegalArgumentException.class, () -> new Lotto(numbers));
    }
}