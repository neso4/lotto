package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import lotto.constant.LottoGameNumber;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 최소 값 미만이면 예외가 발생한다.")
    @Test
    void createLottoByUnderRange() {
        // given
        List<Integer> numbers = List.of(LottoGameNumber.MINIMUM - 1, LottoGameNumber.MINIMUM, 3, 4, 5, 6);

        // when
        ThrowingCallable target = () -> new Lotto(numbers);

        // then
        assertThatIllegalArgumentException().isThrownBy(target);
    }

    @DisplayName("로또 번호가 최대 값을 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByExceedRange() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, LottoGameNumber.MAXIMUM, LottoGameNumber.MAXIMUM + 1);

        // when
        ThrowingCallable target = () -> new Lotto(numbers);

        // then
        assertThatIllegalArgumentException().isThrownBy(target);
    }

    @DisplayName("유효하지 않은 숫자 리스트 테스트")
    @ParameterizedTest(name = "숫자 리스트가 {0}일 경우 예외가 발생한다.")
    @NullAndEmptySource
    void createLottoByNullAndEmpty(List<Integer> numbers) {
        // given

        // when
        ThrowingCallable target = () -> new Lotto(numbers);

        // then
        assertThatIllegalArgumentException().isThrownBy(target);
    }

    @DisplayName("로또 번호는 정렬되어 출력한다.")
    @Test
    void createLottoByDescendingOrder() {
        // given
        List<Integer> numbers = List.of(6, 3, 4, 1, 2, 5);

        // when
        Lotto lotto = new Lotto(numbers);

        // then
        String actual = lotto.toString();
        String expected = "[1, 2, 3, 4, 5, 6]";

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("로또를 match하면 동일한 숫자의 수를 반환한다.")
    @ParameterizedTest(name = "로또 [1, 2, 3, 4, 5, 6]과 {0}일 경우 {1}을 반환한다.")
    @MethodSource("generateOtherLotto")
    void matchLotto(Lotto other, int expected) {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);

        // when
        int actual = lotto.match(other);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("로또 번호 포함 여부 테스트")
    @ParameterizedTest(name = "로또 [1, 2, 3, 4, 5, 6]에서 {0}을 조회하면 {1}이다.")
    @CsvSource(value = {"1:true", "2:true", "3:true", "4:true", "5:true", "6:true",
            "7:false", "8:false", "9:false", "10:false"}, delimiterString = ":")
    void contains(int number, boolean expected) {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);

        // when
        boolean actual = lotto.contains(number);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> generateOtherLotto() {
        return Stream.of(Arguments.of(new Lotto(List.of(1, 2, 3, 10, 11, 12)), 3),
                Arguments.of(new Lotto(List.of(6, 5, 4, 3, 2, 1)), 6));
    }
}
