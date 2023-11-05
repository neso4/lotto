package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

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

    @ParameterizedTest(name = "입력값 : {0}")
    @MethodSource("provideOutOfRangeNumbers")
    @DisplayName("로또 번호 예외 처리: 1 ~ 45 범위를 벗어난 경우")
    void givenOutOfRangeNumbers_whenCreateLotto_then(List<Integer> numbers) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(numbers))
                .withMessageStartingWith("[ERROR]")
                .withMessageContaining("로또 번호는 1 ~ 45 숫자만 가능합니다.");
    }

    private static Stream<Arguments> provideOutOfRangeNumbers() {
        return Stream.of(
                Arguments.of(List.of(0, 1, 2, 3, 4, 45)),
                Arguments.of(List.of(1, 2, 3, 4, 45, 46))
        );
    }

    @Test
    @DisplayName("로또 생성 성공")
    void givenNumbers_whenCreateLotto_thenNoException() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // when & then
        assertThatNoException()
                .isThrownBy(() -> new Lotto(numbers));
    }

    @ParameterizedTest
    @CsvSource(value = {"1:true", "7:false"}, delimiter = ':')
    @DisplayName("로또가 해당 번호를 갖고 있는지 알려준다")
    void givenLottoAndNumber_whenHasNumber_thenReturnTrueOrFalse(int number, boolean expected) {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when
        boolean result = lotto.hasNumber(number);

        // then
        assertEquals(expected, result);
    }

}
