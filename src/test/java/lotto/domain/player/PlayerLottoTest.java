package lotto.domain.player;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.PlayerLotto;
import lotto.domain.lottoManage.PurchaseAmount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class PlayerLottoTest {

    List<Integer> numbers;
    Lotto lotto;
    LottoNumber bonusNumber;
    PurchaseAmount purchaseAmount;
    PlayerLotto player;

    @BeforeEach
    void setUp() {
        // given
        numbers = List.of(1, 2, 3, 4, 5, 6);
        lotto = Lotto.create(numbers);
        bonusNumber = LottoNumber.create(7);
        purchaseAmount = PurchaseAmount.create(8000);

    }

    @Test
    @DisplayName("Player 객체를 생성한다.")
    void create() {
        // when
        player = PlayerLotto.create(lotto, bonusNumber);

        // then
        Assertions.assertThat(player.getLotto().getIntegerNumbers()).isEqualTo(numbers);
        Assertions.assertThat(player.getBonusNumber()).isEqualTo(bonusNumber.getPrimitiveLottoNumber());
    }

    @Test
    @DisplayName("주어진 구입 금액을 로또 가격으로 나누어 구입 가능 로또 개수를 반환한다.")
    void getPurchasedLottoCount() {
        // when
        int purchasedLottoCount = purchaseAmount.calculatePurchasedLottoCount();

        // then
        Assertions.assertThat(purchasedLottoCount).isEqualTo(8);
    }
}