package lotto.model.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderAmountTest {
    @Test
    @DisplayName("로또 금액을 정상적인 가격으로 입력하면 예외가 발생하지 않는다.")
    void orderNormalPrice(){
        OrderAmount order = new OrderAmount("8000");
        int orderAmount = order.amountOfLotto();
        assertThat(orderAmount).isEqualTo(8);
    }

    @Test
    @DisplayName("로또 수익률 계산시 정확한 값인지 확인해 본다.(8천원, 5등)")
    void calculateProfitOf5Place(){
        OrderAmount order = new OrderAmount("8000");
        LottoResults results = new LottoResults( 0, 0, 0, 0, 1);
        assertThat(order.calculateProfit(results)).isEqualTo("62.5");
    }

    @Test
    @DisplayName("로또 수익률 계산시 정확한 값인지 확인해 본다.(1000원, 1등)")
    void calculateProfitOf1Place(){
        OrderAmount order = new OrderAmount("1000");
        LottoResults results = new LottoResults( 1, 0, 0, 0, 0);
        assertThat(order.calculateProfit(results)).isEqualTo("200000000.0");
    }

    @Test
    @DisplayName("로또 수익률 계산시 정확한 값인지 확인해 본다.(10,000원, 미당첨)")
    void calculateProfitOfZero(){
        OrderAmount order = new OrderAmount("10000");
        LottoResults results = new LottoResults( 0, 0, 0, 0, 0);
        assertThat(order.calculateProfit(results)).isEqualTo("0.0");
    }

    @Test
    @DisplayName("구입 금액 입력시 문자를 입력하면 예외가 발생한다.")
    void createOrderAmountByLetter(){
        assertThatThrownBy(() -> new OrderAmount("letter"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입 금액 입력시 문자와 숫자를 같이 입력하면 예외가 발생한다.")
    void createOrderAmountByLetterAndNumber(){
        assertThatThrownBy(() -> new OrderAmount("letter1000"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입 금액 입력시 공백을 입력하면 예외가 발생한다.")
    void createOrderAmountByBlank(){
        assertThatThrownBy(() -> new OrderAmount(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입 금액 입력시 1000원 단위의 숫자가 아니라면 예외가 발생한다.")
    void createOrderAmountByNotMultipleOf1000(){
        assertThatThrownBy(() -> new OrderAmount("1234"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}