package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.constant.StringConstant.COMMA_SPACE;
import static lotto.message.ErrorMessage.EXIST_DUPLICATE;
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

    // 아래에 추가 테스트 작성 가능

    @DisplayName("로또 번호를 입력 구분자로 연결지은 문자열 확인")
    @Test
    public void joinNumbersWithDelimiterTest() {

        Lotto lotto = new Lotto(List.of(6, 1, 5, 2, 4, 3));
        assertThat(lotto.joinNumbersWithDelimiter(COMMA_SPACE)).isEqualTo("1, 2, 3, 4, 5, 6");
    }

    @DisplayName("중복 요소 존재하는지 체크")
    @Test
    public void validateDuplicateTest() {

        assertThatThrownBy(() -> new Lotto(List.of(1, 1, 2, 3, 4, 5))).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(EXIST_DUPLICATE.getMessage());
    }

    @DisplayName("로또와 입력받는 숫자 리스트에서 일치하는 개수 찾기")
    @Test
    public void countCommonElementsBetweenNumbersTest(){
        Lotto lotto = new Lotto(List.of(1,2,3,4,5,6));

        List<Integer> compare = List.of(9,8,7,6,5,4);
        List<Integer> suffle = List.of(6,9,1,3,2,7);

        int countByCompare = lotto.countCommonElementsFromAnotherNumbers(compare);
        int countBySuffle = lotto.countCommonElementsFromAnotherNumbers(suffle);

        assertThat(countByCompare).isEqualTo(3);
        assertThat(countBySuffle).isEqualTo(4);

    }

    @DisplayName("두가지 로또 일치하는 요소 개수 비교")
    @Test
    public void countMatchedNumbersWithLotto(){
        Lotto lotto = new Lotto(List.of(1,2,3,4,5,6));
        Lotto compareLotto = new Lotto(List.of(9,8,7,6,5,4));
        Lotto suffleLotto = new Lotto(List.of(6,9,1,3,2,7));

        int countByCompare = lotto.countCommonNumberFromAnotherLotto(compareLotto);
        int countBySuffle = lotto.countCommonNumberFromAnotherLotto(suffleLotto);

        assertThat(countByCompare).isEqualTo(3);
        assertThat(countBySuffle).isEqualTo(4);
    }



}