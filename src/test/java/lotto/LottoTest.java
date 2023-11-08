package lotto;

import lotto.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    @Test
    void BonusnumTest_숫자테스트(){
        assertThatThrownBy(() -> new BonusNum("귯"))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void BonusnumTest_범위테스트(){
        assertThatThrownBy(() -> new BonusNum("1000"))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void CorrectNum_숫자테스트(){
        assertThatThrownBy(() -> new CorrectNum("1,2,3,4,5,굿"))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void CorrectNum_숫자범위테스트(){
        assertThatThrownBy(() -> new CorrectNum("1,2,3,4,5,83"))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void CorrectNum_로또숫자갯수(){
        assertThatThrownBy(() -> new CorrectNum("1,2,3,4,5,6,7,8"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void BuyingMoney_1000으로_나눠지는지_테스트(){
        assertThatThrownBy(() -> new BuyingMoney("19922"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void BuyingMoney_숫자테스트(){
        assertThatThrownBy(() -> new BuyingMoney("팔천"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void LottoCalculator_lottocalculator테스트(){
        LottoCalculator lottoCalculator = new LottoCalculator();
        lottoCalculator.lottoCalculator(6,false);
        lottoCalculator.lottoCalculator(6,false);
        assertThat(lottoCalculator.getCounts()[0]).isEqualTo(2);
    }
    @Test
    void LottoCalculator_earningPercentage테스트(){
        LottoCalculator lottoCalculator = new LottoCalculator();
        lottoCalculator.lottoCalculator(3,false);
        lottoCalculator.lottoCalculator(3,false);
        assertThat(lottoCalculator.earningPercentage(2)).isEqualTo(500000.0);
    }
}