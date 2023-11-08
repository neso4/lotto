package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.controller.NumberGeneratorVaildation;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    private NumberGeneratorVaildation numbergeneratorvaildation;

    @BeforeEach
    void setUp(){
        numbergeneratorvaildation = new NumberGeneratorVaildation();
    }

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
        
    @DisplayName("로또 번호가 1부터 45사이의 값이 아니면 예외가 발생한다.")
    @Test
    void createLottoByRangeNumber(){
        assertThatThrownBy(() -> new Lotto(List.of(1,2,3,4,5,47)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("정수형식의 문자열 입력시 false 반환")
    @Test
    void 입력_문자열_정수_변환(){
        boolean result = numbergeneratorvaildation.inputBuyCostIntegerVaildation("1000");
        assertThat(result).isEqualTo(false);
    }
    // 아래에 추가 테스트 작성 가능
}