package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StartLottoTest {
    private static StartLotto test;

    @DisplayName("테스트할 StartLotto 객체 먼저 생성")
    @BeforeEach
    void init() {
        test = new StartLotto();
    }

    @DisplayName("구입 금액이 숫자 형식이 아니라면 예외가 발생한다.")
    @Test
    void checkPurchasePriceNotNumberFormat() {
        assertThatThrownBy(() -> {
            String input = "10dkfk";
            test.checkPurchasePrice(input);
        }).isInstanceOf(NumberFormatException.class);
    }

    @DisplayName("구입 금액이 1000원 단위가 아니라면 예외가 발생한다.")
    @Test
    void checkPurchasePriceNot1000wonUnit() {
        assertThatThrownBy(() -> {
            String input = "101";
            test.checkPurchasePrice(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
