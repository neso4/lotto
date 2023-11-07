package lotto.domain;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class LottosTest {
    @Test
    void 로또_한개_생성_저장() {
        Lottos lottos = new Lottos();

        Lotto lotto = lottos.createLotto(() -> List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto).isEqualTo(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        assertThat(lottos.getLottos().size()).isEqualTo(1);
    }
}
