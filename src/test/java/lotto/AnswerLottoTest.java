package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import constants.ErrorMessage;
import constants.NumberType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class AnswerLottoTest {

    @Test
    void 당첨번호_중복_예외_처리_테스트() {
        //Given
        ArrayList<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 5, 5, 6));

        //When & Then
        assertThatThrownBy(() -> new AnswerLotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.LOTTO_HAS_DUPLICATE_NUMBER_ERROR.getMessage());
    }

    @Test
    void 당첨번호_범위_예외_처리_테스트() {
        //Given
        ArrayList<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 50, 5, 6));

        //When & Then
        assertThatThrownBy(() -> new AnswerLotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(ErrorMessage.LOTTO_NOT_IN_RANGE_ERROR.getMessage(),
                        NumberType.MIN_LOTTO_NUMBER.getValue(), NumberType.MAX_LOTTO_NUMBER.getValue()));
    }

    @Test
    void 당첨번호_길이_예외_처리_테스트() {
        //Given
        ArrayList<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 50, 5, 6, 8));

        //When & Then
        assertThatThrownBy(() -> new AnswerLotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(ErrorMessage.LOTTO_LENGTH_ERROR.getMessage(),
                        NumberType.LOTTO_LENGTH.getValue()));
    }

    @Test
    void 발행한_로또와_당첨_번호의_일치하는_숫자의_개수_확인_테스트() {
        //Given
        Lotto lotto = new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)));
        AnswerLotto answerLotto = new AnswerLotto(List.of(1, 2, 3, 4, 5, 6));

        //When
        int expectedResult = 6;
        int result = answerLotto.countMatchNumber(lotto);

        //When & Then
        assertThat(result).isEqualTo(expectedResult);
    }
}
