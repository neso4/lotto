package lotto;

import lotto.model.GetLottoPurchase;
import lotto.model.Lotto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
   /* @DisplayName("구입 금액이 숫자가 아닐 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1500","123f","-1"})
    void purchaseCostByNotNumber(String inputCost) {
        assertThatThrownBy(() -> new GetLottoPurchase(inputCost))
                .isInstanceOf(IllegalArgumentException.class);
    }*/

    @DisplayName("구입 금액에 맞는 숫자가 들어갔을 경우 성공")
    @ParameterizedTest
    @ValueSource(strings = {"15000","100000","5000"})
    void purchaseCostByCorrectNumber(String inputCost) {
        GetLottoPurchase getLottoPurchase = new GetLottoPurchase(inputCost);
        Assertions.assertThat(getLottoPurchase.getCost()).isEqualTo(Integer.parseInt(inputCost));
    }
}