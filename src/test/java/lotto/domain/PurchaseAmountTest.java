package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.util.Constant.*;
import static lotto.domain.PurchaseAmount.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PurchaseAmountTest {

    @ParameterizedTest
    @ValueSource(strings = {"500O", "500X", "X5000"})
    @DisplayName("구입 금액에 숫자 이외의 값이 입력된 경우에 대한 예외 테스트")
    public void purchaseMoneyNumberFormatExceptionTest(String money){
        //given

        //when

        //then
        assertThatThrownBy(() -> validateMoneyType(money))
                .isInstanceOf(NumberFormatException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-50000, 0 , -1000})
    @DisplayName("구입 금액이 0원 이하일(0장보다 덜 구매하는) 경우에 대한 예외 테스트")
    public void ifMoneyIsLessThanZeroExceptionTest(int money){
        //given

        //when

        //then
        assertThatThrownBy(() -> validateMoneyNotMoreThanZero(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1001, 1010, 5009})
    @DisplayName("구입 금액을 1000원 단위로 입력하지 않은 경우에 대한 예외 테스트")
    public void purchaseMoneyDivideExceptionTest(int money){
        //given

        //when

        //then
        assertThatThrownBy(() -> validateMoneyDivide(money, DIVIDER_FOR_COUNT_PURCHASE_QUANTITY))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
