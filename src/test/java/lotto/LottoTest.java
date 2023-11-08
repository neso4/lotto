package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoScore;
import lotto.util.NumberUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
    @DisplayName("로또 객체 생성 성공 케이스")
    void 로또_객체_생성_성공() {
        Lotto lotto = new Lotto(NumberUtil.numberGenerator(1, 45, 6));
        assertThat(lotto).isInstanceOf(Lotto.class);
    }

    @Test
    @DisplayName("로또 번호 COMPARE 성공 케이스")
    void 로또_번호_COMPARE_성공() {
        Lotto lotto = new Lotto(NumberUtil.numberGenerator(1, 45, 6));
        assertThat(lotto.compare(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7)).isInstanceOf(LottoScore.class);

    }

    @Test
    @DisplayName("로또 번호 VIEW 성공 케이스")
    void 로또_번호_VIEW_성공() {
        Lotto lotto = new Lotto(NumberUtil.numberGenerator(1, 45, 6));
        assertThatCode(() -> lotto.viewNumberStatus())
                .doesNotThrowAnyException();
    }
}