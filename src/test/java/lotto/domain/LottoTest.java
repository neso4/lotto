package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @ParameterizedTest
    @MethodSource("generateTestLottoInput")
    void 로또를_생성한다(List<Integer> input) {
        // when
        Lotto result = Lotto.from(input);
        // then
        assertThat(result).isInstanceOf(Lotto.class);
    }

    @Test
    void 로또번호_6개_초과로_주면_예외를반환() {
        assertThatThrownBy(() -> Lotto.from(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또번호_6개_미만으로_주면_예외를반환() {
        assertThatThrownBy(() -> Lotto.from(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또번호에_중복된숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> Lotto.from(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또번호의_번호가_1_45이내가_아니라면_예외가_발생한다() {
        assertThatThrownBy(() -> Lotto.from(List.of(100, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @ParameterizedTest
    @MethodSource("generateTestLottoInput")
    void createAutoLottoNumbers는_자동으로_로또를생성한다(List<Integer> input) {
        // given
        Generator generator = () -> input;
        // when
        Lotto result = Lotto.createAutoLottoNumbers(generator);
        // then
        assertThat(result).isInstanceOf(Lotto.class);
    }

    static Stream<List<Integer>> generateTestLottoInput() {
        return Stream.of(
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44)
        );
    }

}