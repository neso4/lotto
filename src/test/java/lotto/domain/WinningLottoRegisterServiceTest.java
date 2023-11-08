package lotto.domain;

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
        winningLotto = service.registerNumbers("1,2,3,4,5,6");
        assertThat(winningLotto.getNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("보너스 번호 등록")
    void 보너스_번호_등록() {
        winningLotto = service.registerNumbers("1,2,3,4,5,6");
        winningLotto = service.registerBonusNumber("7");
        assertThat(winningLotto.getBonusNumber()).isEqualTo(7);
    }

    @Test
    @DisplayName("로또번호가 숫자가 아닌 경우 예외 처리")
    void 로또번호가_숫자가_아닐시_예외처리() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> service.registerNumbers("a,b,c,d,e,f"))
                .withMessage("로또 번호는 숫자여야 합니다.");
    }
}
