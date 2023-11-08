package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoRegisterServiceTest {
    private WinningLottoRegisterService service;
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        service = new WinningLottoRegisterService();
    }

    @Test
    @DisplayName("당첨번호 등록")
    void 당첨번호_등록() {
        winningLotto = service.registerNumbers(List.of(1, 2, 3, 4, 5, 6));
        assertThat(winningLotto.getNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("보너스 번호 등록")
    void 보너스_번호_등록() {
        winningLotto = service.registerBonusNumber(7);
        assertThat(winningLotto.getBonusNumber()).isEqualTo(7);
    }
}