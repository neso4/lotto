package lotto;

import lotto.domain.model.Lotto;
import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

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
    @DisplayName("발행된 로또 번호를 반환한다.")
    @Test
    void getLottoNumbers() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto.getNumbers())
                .contains(1,2,3,4,5,6);

        assertThat(lotto.getNumbers())
                .contains(1,2,4,3,6,5);

    }

    @DisplayName("발행된 로또 번호를 보여준다.")
    @Test
    void showLottoNumbers() {
        System.setOut(new PrintStream(outContent));

        Lotto lotto = new Lotto(List.of(1, 2, 3, 5, 4, 6));
        lotto.showNumbers();


        assertThat(outContent.toString().trim()).isEqualTo("[1, 2, 3, 4, 5, 6]");


        System.setOut(originalOut);
    }
}