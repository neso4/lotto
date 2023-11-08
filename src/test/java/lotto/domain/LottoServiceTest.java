package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import lotto.domain.dto.LottoInfos;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoServiceTest {
    private LottoService lottoService;
    @BeforeEach
    void beforeEach() {
        lottoService = new LottoService(() -> List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("지불금액을_1000원으로_나눈_수만큼_로또_생성")
    @Test
    void 지불금액을_1000원으로_나눈_수만큼_로또_생성() {
        LottoInfos lottoInfos = lottoService.createLottos("2000");

        assertThat(lottoInfos.getLottoInfos().size()).isEqualTo(2);
    }

    @Test
    void 지불금액이_1000원_단위가_아니면_예외를_던진다() {
        ;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> lottoService.createLottos("2222"))
                .withMessage("로또 구입 금액은 1000원 단위여야 합니다.");
    }
}
