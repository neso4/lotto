package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Lotto;

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

    @DisplayName("로또 번호가 1-45사이의 숫자가 아니면 예외가 발생한다.")
    @Test
    void createLottoByInRange() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 55)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호를 조회할때 오름차순으로 정렬된다.")
    @Test
    void findLottoBySortedNumber() {
        Lotto lotto = new Lotto(List.of(6, 5, 4, 3, 2, 1));
        assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }
}