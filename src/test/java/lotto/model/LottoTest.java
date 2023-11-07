package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.constant.ErrorMessage;
import lotto.util.CommaParser;
import lotto.validator.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    // 아래에 추가 테스트 작성 가능

    @DisplayName("쉼표 사이에 당첨 번호가 공백일 경우 예외가 발생한다.")
    @Test
    void 당첨_번호_숫자_공백_실패_테스트() {
        String input = "1,2,,3,4,5";
        String expectedErrorMessage = ErrorMessage.INPUT_EMPTY.get();

        assertThatThrownBy(() -> createLottoNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedErrorMessage);
    }

    @DisplayName("당첨 번호 중 숫자가 아닌 입력이 있을 경우 예외가 발생한다.")
    @Test
    void 당첨_번호_숫자_아님_실패_테스트() {
        String input = "2,3,a,5,-,1";
        String expectedErrorMessage = ErrorMessage.INPUT_NOT_NUMBER.get();

        assertThatThrownBy(() -> createLottoNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedErrorMessage);
    }

    @DisplayName("당첨 번호가 6개가 아닐 경우 예외가 발생한다.")
    @Test
    void 당첨_번호_숫자_개수_실패_테스트() {
        String input = "1,2,3,4,5";
        String expectedErrorMessage = ErrorMessage.LOTTO_NUMBER_LENGTH.get();

        List<Integer> numbers = createLottoNumbers(input);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedErrorMessage);
    }

    @DisplayName("당첨 번호 중 1과 45사이의 숫자가 아닐 경우 예외가 발생한다.")
    @Test
    void 당첨_번호_숫자_범위_실패_테스트() {
        String input = "140,1,2,3,4,5";
        String expectedErrorMessage = ErrorMessage.LOTTO_NUMBER_RANGE.get();

        List<Integer> numbers = createLottoNumbers(input);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedErrorMessage);
    }

    @DisplayName("당첨 번호 중 중복된 숫자가 존재할 경우 예외가 발생한다.")
    @Test
    void 당첨_번호_숫자_중복_실패_테스트() {
        String input = "1,1,2,3,4,5";
        String expectedErrorMessage = ErrorMessage.LOTTO_NUMBER_DUPLICATE.get();

        List<Integer> numbers = createLottoNumbers(input);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedErrorMessage);
    }

    @DisplayName("당첨 번호가 문제 조건에 알맞게 입력된 경우 테스트가 성공한다.")
    @Test
    void 당첨_번호_숫자_성공_테스트() {
        String input = "1,2,3,4,5,6";
        List<Integer> expectedResult = List.of(1, 2, 3, 4, 5, 6);

        List<Integer> numbers = createLottoNumbers(input);
        Lotto lotto = new Lotto(numbers);
        List<Integer> actualResult = lotto.getNumbers();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private List<Integer> createLottoNumbers(String input) {
        List<String> splitInputByComma = CommaParser.parse(input);
        return splitInputByComma.stream()
                .map(number -> {
                    InputValidator.validateEmpty(number);
                    return InputValidator.validateNumber(number);
                }).toList();
    }
}