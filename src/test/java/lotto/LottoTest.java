package lotto;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @DisplayName("중복 없는 6자리 수의 로또 번호를 생성한다.")
    @Test
    void distinctLottoNumber() {
        // when
        Lotto lotto = Lotto.createLotto();
        List<Integer> numbers = lotto.getNumbers();
        Set<Integer> distinctNumbers = new HashSet<>(numbers);

        // then
        assertThat(numbers.size())
                .isEqualTo(distinctNumbers.size())
                .isEqualTo(6);
    }

    @DisplayName("생성된 로또 번호는 오름차순으로 정렬된다.")
    @Test
    void orderByAsc() {
        // when
        Lotto lotto = Lotto.createLotto();
        List<Integer> numbers = lotto.getNumbers();

        // then
        assertThat(numbers.get(0)).isLessThan(numbers.get(1));
        assertThat(numbers.get(1)).isLessThan(numbers.get(2));
        assertThat(numbers.get(2)).isLessThan(numbers.get(3));
        assertThat(numbers.get(3)).isLessThan(numbers.get(4));
        assertThat(numbers.get(4)).isLessThan(numbers.get(5));
    }

    @DisplayName("생성된 로또 번호는 1이상 45이하의 숫자로 구성된다.")
    @Test
    void lottoNumberRange() {
        // when
        Lotto lotto = Lotto.createLotto();
        List<Integer> numbers = lotto.getNumbers();

        // then 1
        assertThat(numbers.get(0)).isGreaterThan(0);
        assertThat(numbers.get(1)).isGreaterThan(0);
        assertThat(numbers.get(2)).isGreaterThan(0);
        assertThat(numbers.get(3)).isGreaterThan(0);
        assertThat(numbers.get(4)).isGreaterThan(0);
        assertThat(numbers.get(5)).isGreaterThan(0);

        // then 2
        assertThat(numbers.get(0)).isLessThan(46);
        assertThat(numbers.get(1)).isLessThan(46);
        assertThat(numbers.get(2)).isLessThan(46);
        assertThat(numbers.get(3)).isLessThan(46);
        assertThat(numbers.get(4)).isLessThan(46);
        assertThat(numbers.get(5)).isLessThan(46);
    }
}