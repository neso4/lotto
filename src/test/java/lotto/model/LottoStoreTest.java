package lotto.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import lotto.model.lottogenerator.RandomLottoGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoStoreTest {
    LottoStore lottoStore = new LottoStore(new RandomLottoGenerator());

    @ParameterizedTest
    @ValueSource(ints = {1000, 5000, 8000, 14000, 144000})
    @DisplayName("입력한 값 만큼 로또를 판매한다.")
    public void normal_purchase(int money) {
        //given
        //when
        Lottos lottos = lottoStore.purchase(money);

        //then
        Assertions.assertThat(lottos.getLottosDTO().size())
                .isEqualTo(money / 1000);
    }

}