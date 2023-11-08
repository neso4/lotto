package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class WinningCheckerTest {

    @DisplayName("WinningInfo 객체의 당첨 정보를 바탕으로 등수별 당첨 정보 생성")
    @Test
    void successCheckWinning(){
        Customer customer = Customer.createCustomer(5000);
        customer.buyLottos();
        Target target = Target.createTarget(List.of(1, 2, 3, 4, 5, 6), 7);
        customer.calculateWinning(target);

        int countOfWinningInfo = (int)customer.getWinningInfos().stream()
                .count();

        WinningChecker winningChecker = WinningChecker.createWinningChecker();
        assertDoesNotThrow(() -> winningChecker.checkWinning(customer));

        int createdCountOfWinningInfo = winningChecker.getRankInfo().values().stream()
                .mapToInt(Integer::valueOf)
                .sum();

        assertThat(countOfWinningInfo).isEqualTo(createdCountOfWinningInfo);

    }
}