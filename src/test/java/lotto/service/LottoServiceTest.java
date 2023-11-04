package lotto.service;

import static lotto.util.TestUtil.ERROR_PREFACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import lotto.model.Lotto;
import lotto.model.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoServiceTest extends NsTest {

    LottoService lottoService;

    @BeforeEach
    void setup() {
        this.lottoService = new LottoService();
    }

    @DisplayName("구매 금액이 1000원 단위가 아니면 예외 발생")
    @Test
    void purchaseAmountByNotUnit() {
        final Money money = new Money("1100");
        final LottoService service = new LottoService();

        assertThatThrownBy(() -> service.validateUnit(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFACE);
    }

    @DisplayName("구매 금액이 1000원 단위면 오류가 발생하지 않는다.")
    @Test
    void validateUnitSuccess() {
        final Money money = new Money("11000");
        final LottoService service = new LottoService();

        service.validateUnit(money);
    }

    @DisplayName("로또가 정상적으로 생성된다.")
    @Test
    void makeLotto() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = getAccessibleMethod("makeLotto");
        Lotto lotto = (Lotto) method.invoke(lottoService);

        for (int num :
                lotto.getNumbers()) {
            assertThat(num)
                    .isNotNull(); // 숫자가 비어있지 않다.
        }
    }

    @DisplayName("로또를 갯수만큼 생성한다.")
    @Test
    void issuanceLotto() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final int count = 10;
        Method method = getAccessibleMethod("issuanceLotto", int.class);
        List<Lotto> lottos = (List<Lotto>) method.invoke(lottoService, count);

        assertThat(lottos.size()).isEqualTo(count);
    }

    @DisplayName("입력한 돈 만큼 로또를 구입한다.")
    @Test
    void buyLotto() {
        final Money money = new Money("5000");
        List<Lotto> lottos = lottoService.buyLottos(money);

        assertThat(lottos.size())
                .isEqualTo(money.getMoney() / 1000);
    }

    private Method getAccessibleMethod(String methodName, Class<?>... parameterTypes)
            throws NoSuchMethodException {
        Method method = lottoService.getClass().getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);

        return method;
    }

    @Override
    public void runMain() {
    }
}