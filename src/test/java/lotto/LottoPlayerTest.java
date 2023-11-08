package lotto;

import lotto.Controller.ErrorMessage;
import lotto.Model.LottoPlayer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoPlayerTest {




    @ParameterizedTest
    @CsvSource({
            "1000, 1",
            "5000, 5",
            "99000, 99",
            "990000, 990"
    })
    public void 금액에_대한_로또_구매갯수_확인(Integer cache,Integer answerQuantity) {
        LottoPlayer lottoPlayer = new LottoPlayer(cache);

        int purchaseQuantity = lottoPlayer.orderLottos();

        Assertions.assertThat(purchaseQuantity).isEqualTo(answerQuantity);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,-1, -100, -9802})
    public void 금액이_0원_이하인_경우_예외처리(Integer cache) {
        Assertions.assertThatThrownBy(() -> new LottoPlayer(cache))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.CASH_NOT_POSITIVE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {3245, 100, 98200,37002})
    void 금액이_천원단위가_아닌_경우_예외처리(Integer cache) {
        Assertions.assertThatThrownBy(() -> new LottoPlayer(cache))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.CASH_NOT_THOUSAND.getMessage());;
    }


}
