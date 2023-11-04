package lotto.application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import lotto.domain.LottoMachine;
import lotto.dto.WinningNumbersDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoServiceTest {

    @DisplayName("구입 금액에 따라 구매 가능한 로또 티켓 수를 계산한다.")
    @Test
    void calculateNumberOfLottoTicketsBasedOnPurchaseAmount() {
        // given
        LottoMachine lottoMachine = new LottoMachine();
        LottoService lottoService = new LottoService(lottoMachine);
        int purchaseAmount = 14000;

        // when
        lottoService.purchaseLottoTickets(purchaseAmount);

        // then
        assertThat(lottoService.getLottoDtos().size()).isEqualTo(14);
    }
    @DisplayName("당첨 번호와 보너스 번호를 처리하여 WinningNumbers 객체를 생성한다.")
    @Test
    void processWinningNumbers_CreatesWinningNumbersObject() {
        // given
        LottoService lottoService = new LottoService(new LottoMachine());
        List<Integer> winningNumbersInput = List.of(3, 6, 9, 12, 15, 18);
        int bonusNumber = 21;

        // when
        lottoService.processWinningNumbers(winningNumbersInput, bonusNumber);

        // then
        WinningNumbersDto winningNumbersDto = lottoService.getWinningNumbersDto();
        assertEquals(winningNumbersInput, winningNumbersDto.winningNumbers());
        assertEquals(bonusNumber, winningNumbersDto.bonusNumber());
    }
}