package lotto;

import lotto.model.Lotto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void 로또구매_금액_0원_Null_값_예외처리() {
        assertThatThrownBy(() ->
                Assertions.assertThat("").isInstanceOf(IllegalArgumentException.class));
    }

    @Test
    void 로또구매_금액_1000원_단위_예외처리() {
        assertThatThrownBy(() ->
                assertThat(1100).isInstanceOf(IllegalArgumentException.class));
    }

    @Test
    void 로또번호_입력_6개미만_예외처리() {
        assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또번호_입력_1부터_45사이_입력이외_예외처리() {
        assertThatThrownBy(() -> new Lotto(List.of(1,46,0,4,5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

}