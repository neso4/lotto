package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 6개의 숫자로만 구성되어 있어야 합니다.");
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 중복되지 않은 숫자로 구성되어 있어야 합니다.");
    }

    @DisplayName("로또 번호에 1~45 범위가 아닌 숫자가 있으면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("getLottos")
    void createLottoByOutOfRangeNumber(List<Integer> lottos) {
        assertThatThrownBy(() -> new Lotto(lottos))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 1 ~ 45 범위의 숫자로 구성되어 있어야 합니다.");
    }

    static Stream<List<Integer>> getLottos() {
        return Stream.of(
                List.of(1, 2, 3, 4, 5, 55),
                List.of(100, 22, 34, 45, 44, 5),
                List.of(6, 8, 9, 87, 34, 55)
        );
    }

    @DisplayName("오름차순으로 로또 번호를 얻어와야 한다.")
    @Test
    void getLottoOrderByAscTest() {
        // given
        List<Integer> numbers = List.of(5, 2, 3, 4, 6, 1);
        List<Integer> sortedNumbers = numbers.stream()
                .sorted()
                .toList();
        NumberPicker randomNumbers = () -> numbers;

        // when
        Lotto lotto = new Lotto(randomNumbers.pick());

        // then
        assertThat(lotto.getSortedNumbers()).isEqualTo(sortedNumbers);
    }

    @DisplayName("등수를 계산한다")
    @ParameterizedTest
    @MethodSource("getWinningLottos")
    void judgeTest(Map<Integer, List<Integer>> WinningLottos) {
        // given
        NumberPicker picker = () -> List.of(1, 2, 3, 4, 5, 6);
        NumberPicker winningPicker = () -> null;
        int rank = -1;
        int bonusNumber = -1;

        for (Entry<Integer, List<Integer>> pair : WinningLottos.entrySet()) {
            List<Integer> value = pair.getValue();

            rank = pair.getKey();
            bonusNumber = value.get(value.size() - 1);
            winningPicker = () -> value.subList(0, 6);
        }

        Lotto lotto = new Lotto(picker.pick());
        Lotto winningLotto = new Lotto(winningPicker.pick());

        // when
        int result = lotto.judge(winningLotto, new BonusNumber(bonusNumber));

        // then
        assertThat(result).isEqualTo(rank);
    }

    static Stream<Map<Integer, List<Integer>>> getWinningLottos() {
        return Stream.of(
                Map.of(1, List.of(1, 2, 3, 4, 5, 6, 45)),
                Map.of(2, List.of(1, 2, 3, 4, 5, 44, 6)),
                Map.of(3, List.of(1, 2, 3, 4, 5, 44, 45)),
                Map.of(4, List.of(1, 2, 3, 4, 43, 44, 45)),
                Map.of(5, List.of(1, 2, 3, 42, 43, 44, 45))
        );
    }
}