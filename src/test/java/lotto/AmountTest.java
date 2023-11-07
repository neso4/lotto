package lotto;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import domain.Amount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AmountTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    Amount amount;

    @BeforeEach
    public void setUp(){
        amount = new Amount(8000);
    }

    @DisplayName("로또 구입 금액이 1000으로 나누어 떨어지지 않으면 예외가 발생한다.")
    @Test
    void createAmountByDivideNumber() {
        assertSimpleTest(() -> {
            runException("100");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("로또 구입 금액이 0 이하면 예외가 발생한다.")
    @Test
    void createAmountByNegative() {
        assertSimpleTest(() -> {
            runException("-1");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void calculateProfitRateTest(){
        double profitRate = amount.calculateProfitRate(5000);

        assertThat(profitRate).isEqualTo(62.5);
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
