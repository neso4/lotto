package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import lotto.domain.entity.Lotto;
import lotto.domain.entity.Lottos;
import lotto.domain.entity.ThousandUnitMoney;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    public void 자동_로또리스트_생성() {
        // Given
        ThousandUnitMoney money = ThousandUnitMoney.create(8000);
        int oneLottoPrice = 1000;

        // When
        Lottos result = Lottos.createAutomatic(money, oneLottoPrice);

        // Then
        assertThat(result.size()).isEqualTo(8);
    }
}
