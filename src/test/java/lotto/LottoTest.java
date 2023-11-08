package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("toString() 호출 시 로또 숫자 배열 String 반환한다.")
    @Test
    void toStringLotto() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        String actual = lotto.toString();
        String expected = "[1, 2, 3, 4, 5, 6]";

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("로또 숫자 배열 오름차순 정렬 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"6,5,4,3,2,1", "1,6,4,3,2,5"})
    void sortLottoNumbers(String numbersCsv) {
        List<Integer> numbers = Arrays.stream(numbersCsv.split(","))
                .map(String::trim)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        Lotto lotto = new Lotto(numbers);

        String actual = lotto.toString();
        String expected = "[1, 2, 3, 4, 5, 6]";

        assertThat(actual).isEqualTo(expected);
    }
}
