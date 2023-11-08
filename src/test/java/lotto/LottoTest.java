package lotto;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    @DisplayName("새로운 로또를 생성하고 크기를 확인한다.")
    @Test
    void makeNewLotto(){
        Lotto lotto = Lotto.makeLottoWithRandomNumbers();
        System.out.println(Arrays.toString(lotto.getNumbers().toArray()));
        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @DisplayName("당첨 번호를 입력받아 결과를 출력한다.")
    @Test
    void countMatchedNumbers(){
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> selectedNumebers = List.of(1, 2, 3, 7, 8, 9);
        int bonusNumeber = 2;
        assertThat(lotto.countResult(selectedNumebers, bonusNumeber)).isEqualTo(RESULT.THREE_STRIKE);
    }

}