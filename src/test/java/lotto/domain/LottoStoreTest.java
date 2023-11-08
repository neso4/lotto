package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.constant.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoStoreTest {

    private final NumberGenerator mockNumberGenerator = new NumberGenerator() {
        @Override
        public List<Integer> generateNumbers() {
            return IntStream.rangeClosed(1, 6).boxed().collect(Collectors.toList());
        }
    };

    @Test
    @DisplayName("정상적인 투자 금액으로 로또 구매 시 중복되지 않는 번호 생성")
    public void testPurchaseLottosWithValidInvestmentAmount() {
        LottoStore lottoStore = LottoStore.from(mockNumberGenerator);
        List<Lotto> lottos = lottoStore.purchaseLottos(2000);

        lottos.forEach(lotto -> {
            List<Integer> numbers = lotto.numbers();
            assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
        });
    }

    @Test
    @DisplayName("LOTTO_PRICE보다 적은 투자 금액 예외 처리")
    public void testPurchaseLottosWithLessThanLottoPrice() {
        LottoStore lottoStore = LottoStore.from(mockNumberGenerator);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> lottoStore.purchaseLottos(500))
            .withMessage(ErrorMessage.INVALID_INVESTMENT_AMOUNT.getMessage());
    }

    @Test
    @DisplayName("LOTTO_PRICE로 나누어 떨어지지 않는 투자 금액 예외 처리")
    public void testPurchaseLottosWithInvalidInvestmentAmount() {
        LottoStore lottoStore = LottoStore.from(mockNumberGenerator);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> lottoStore.purchaseLottos(2500))
            .withMessage(ErrorMessage.INVALID_INVESTMENT_AMOUNT.getMessage());
    }
}
