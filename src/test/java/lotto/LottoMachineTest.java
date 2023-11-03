package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {
    @DisplayName("5천원일 경우 로또 5개를 발행한다.")
    @Test
    void makeFiveWithFiveThousand() {
        Money fiveThousand = new Money(5_000);
        LottoMachine lottoMachine = new LottoMachine();
        List<Lotto> lottos = lottoMachine.makeLottosWith(fiveThousand);
        assertThat(lottos).hasSize(5);
    }
}
