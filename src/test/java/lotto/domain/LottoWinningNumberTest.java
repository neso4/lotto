package lotto.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.domain.constant.LottoConstant;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoWinningNumberTest {

    @Test
    public void 로또_번호로_LottoWinningNumberCreate_를_생성할_수_있다() throws Exception {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 45);

        // when
        // then
        assertThatCode(() -> {
            new LottoWinningNumber(numbers);
        }).doesNotThrowAnyException();
    }

    @Test
    public void 로또_번호_갯수가_다르면_LottoWinningNumberCreate_를_생성_시_예외가_발생한다() throws Exception {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        // when
        // then
        assertThatThrownBy(() -> {
            new LottoWinningNumber(numbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 로또_번호_갯수가_중복되면_LottoWinningNumberCreate_를_생성_시_예외가_발생한다() throws Exception {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        // when
        // then
        assertThatThrownBy(() -> {
            new LottoWinningNumber(numbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 범위_초과의_로또_번호로_LottoWinningNumberCreate_를_생성_시_예외가_발생한다() throws Exception {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, LottoConstant.LOTTO_END_NUMBER + 1);

        // when
        // then
        assertThatThrownBy(() -> {
            new LottoWinningNumber(numbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 범위_미만의_로또_번호로_LottoWinningNumberCreate_를_생성_시_예외가_발생한다() throws Exception {
        // given
        List<Integer> numbers = List.of(LottoConstant.LOTTO_START_NUMBER - 1, 2, 3, 4, 5, 45);

        // when
        // then
        assertThatThrownBy(() -> {
            new LottoWinningNumber(numbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void containsNumber_호출_시_번호가_포함되어_있으면_true_를_반환한다() throws Exception {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(numbers);

        // when
        boolean contains = lottoWinningNumber.containsNumber(1);

        // then
        assertThat(contains).isEqualTo(true);
    }

    @Test
    public void containsNumber_호출_시_번호가_포함되지_않았으면_false_를_반환한다() throws Exception {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(numbers);

        // when
        boolean contains = lottoWinningNumber.containsNumber(45);

        // then
        assertThat(contains).isEqualTo(false);
    }
}