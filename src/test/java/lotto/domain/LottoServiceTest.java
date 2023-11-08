package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import lotto.domain.dto.LottoInfos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoServiceTest {
    @DisplayName("지불금액을_1000원으로_나눈_수만큼_로또_생성")
    @Test
    void 지불금액을_1000원으로_나눈_수만큼_로또_생성() {
        LottoService lottoService = new LottoService(() -> List.of(1, 2, 3, 4, 5, 6));

        LottoInfos lottoInfos = lottoService.createLottos("2000");

        assertThat(lottoInfos.getLottoInfos().size()).isEqualTo(2);
    }
}
