package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.constant.LottoConstant;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoPurchaseTest {

    @Test
    public void 로또_1장_금액으로_나누어지는_금액으로_LottoPurchase_생성이_가능하다() throws Exception {
        // given
        long purchaseAmount = LottoConstant.LOTTO_TICKET_PRICE * 8;

        // when
        // then
        assertThatCode(() -> {
            new LottoPurchase(purchaseAmount);
        }).doesNotThrowAnyException();
    }

    @Test
    public void 로또_1장_금액으로_나누어지지_않는_금액으로_LottoPurchase_생성시_예외가_발생한다() throws Exception {
        // given
        long purchaseAmount = LottoConstant.LOTTO_TICKET_PRICE * 8 + LottoConstant.LOTTO_TICKET_PRICE / 2;

        // when
        // then
        assertThatThrownBy(() -> {
            new LottoPurchase(purchaseAmount);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 구입_금액이_양수가_아니면_LottoPurchase_생성시_예외가_발생한다() throws Exception {
        // given
        long purchaseAmount = 0;

        // when
        // then
        assertThatThrownBy(() -> {
            new LottoPurchase(purchaseAmount);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void calculateTickets_메서드_호출_시_구매_금액_나누기_티켓_한장_금액_만큼의_티켓_갯수를_반환한다() throws Exception {
        // given
        long purchaseAmount = LottoConstant.LOTTO_TICKET_PRICE * 9;

        // when
        LottoPurchase lottoPurchase = new LottoPurchase(purchaseAmount);
        long numTickets = lottoPurchase.countTickets();

        //then
        assertThat(numTickets).isEqualTo(9);
    }


}