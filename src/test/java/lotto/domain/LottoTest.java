package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호와 보너스 번호가 중복되면 예외 발생")
    @Test
    void checkInclusionOfBonusNumber() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when, then
        assertThatThrownBy(() -> lotto.checkInclusion(6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력된 로또 번호가 숫자가 아닐 때, 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {"a", " ", ""})
    void checkLottoNumberByNotNumber(String lottoNumber) {
        assertThatThrownBy(() -> Lotto.checkLottoNumber(lottoNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력된 로또 번호가 정수가 아닐 때, 예외 처리 ")
    @ParameterizedTest
    @ValueSource(strings = {"1.1","10.0"})
    void checkLottoNumberByNotInteger(String lottoNumber) {
        assertThatThrownBy(() -> Lotto.checkLottoNumber(lottoNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력된 로또 번호가 로또 범위를 벗어날 때, 예외 처리 ")
    @ParameterizedTest
    @ValueSource(strings = {"-1","0","46"})
    void checkLottoNumberByNotRange(String lottoNumber) {
        assertThatThrownBy(() -> Lotto.checkLottoNumber(lottoNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력된 로또 번호 앞에 0이 포함될 때, 예외 처리 ")
    @ParameterizedTest
    @ValueSource(strings = {"01","00000010"})
    void checkLottoNumberByFirstNumberZero(String lottoNumber) {
        assertThatThrownBy(() -> Lotto.checkLottoNumber(lottoNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}