package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoOneSetTest {

    @DisplayName("LottoOneSet와 Lotto가 같은 객체인지 검증합니다.")
    @Test
    void testLottoOneSetWithLotto(){
        LottoOneSet lottoOneSet = new LottoOneSet();
        Lotto lotto = new Lotto(lottoOneSet.getLottoOneSet());
        assertThat(lottoOneSet.getLottoOneSet()).isEqualTo(lotto.getNumbers());
    }
}
