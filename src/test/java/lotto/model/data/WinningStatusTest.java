package lotto.model.data;

import static lotto.util.ExceptionMessage.INVALID_PRIZE_MONEY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningStatusTest {

    WinningStatus winningStatus;

    @BeforeEach
    void beforeEach() {
        winningStatus = new WinningStatus();
    }

    @Test
    @DisplayName("일치하는 숫자가 3개라면 5등 당첨 수가 증가한다.")
    void fifthPlace() {
        // given
        Integer firstPlace = winningStatus.countOfPlace(1);
        Integer secondPlace = winningStatus.countOfPlace(2);
        Integer thirdPlace = winningStatus.countOfPlace(3);
        Integer fourthPlace = winningStatus.countOfPlace(4);
        Integer fifthPlace = winningStatus.countOfPlace(5);

        // when
        winningStatus.recordWinning(3, 0);

        // then
        assertThat(winningStatus.countOfPlace(5)).isEqualTo(fifthPlace + 1);
        assertThat(winningStatus.countOfPlace(4)).isEqualTo(fourthPlace);
        assertThat(winningStatus.countOfPlace(3)).isEqualTo(thirdPlace);
        assertThat(winningStatus.countOfPlace(2)).isEqualTo(secondPlace);
        assertThat(winningStatus.countOfPlace(1)).isEqualTo(firstPlace);
    }

    @Test
    @DisplayName("일치하는 숫자가 4개라면 4등 당첨 수가 증가한다.")
    void fourthPlace() {
        // given
        Integer firstPlace = winningStatus.countOfPlace(1);
        Integer secondPlace = winningStatus.countOfPlace(2);
        Integer thirdPlace = winningStatus.countOfPlace(3);
        Integer fourthPlace = winningStatus.countOfPlace(4);
        Integer fifthPlace = winningStatus.countOfPlace(5);

        // when
        winningStatus.recordWinning(4, 0);

        // then
        assertThat(winningStatus.countOfPlace(5)).isEqualTo(fifthPlace);
        assertThat(winningStatus.countOfPlace(4)).isEqualTo(fourthPlace + 1);
        assertThat(winningStatus.countOfPlace(3)).isEqualTo(thirdPlace);
        assertThat(winningStatus.countOfPlace(2)).isEqualTo(secondPlace);
        assertThat(winningStatus.countOfPlace(1)).isEqualTo(firstPlace);
    }

    @Test
    @DisplayName("일치하는 숫자가 5개라면 3등 당첨 수가 증가한다.")
    void thirdPlace() {
        // given
        Integer firstPlace = winningStatus.countOfPlace(1);
        Integer secondPlace = winningStatus.countOfPlace(2);
        Integer thirdPlace = winningStatus.countOfPlace(3);
        Integer fourthPlace = winningStatus.countOfPlace(4);
        Integer fifthPlace = winningStatus.countOfPlace(5);

        // when
        winningStatus.recordWinning(5, 0);

        // then
        assertThat(winningStatus.countOfPlace(5)).isEqualTo(fifthPlace);
        assertThat(winningStatus.countOfPlace(4)).isEqualTo(fourthPlace);
        assertThat(winningStatus.countOfPlace(3)).isEqualTo(thirdPlace + 1);
        assertThat(winningStatus.countOfPlace(2)).isEqualTo(secondPlace);
        assertThat(winningStatus.countOfPlace(1)).isEqualTo(firstPlace);
    }

    @Test
    @DisplayName("일치하는 숫자가 5개이고 보너스 볼이 일치하면 2등 당첨 수가 증가한다.")
    void secondPlace() {
        // given
        Integer firstPlace = winningStatus.countOfPlace(1);
        Integer secondPlace = winningStatus.countOfPlace(2);
        Integer thirdPlace = winningStatus.countOfPlace(3);
        Integer fourthPlace = winningStatus.countOfPlace(4);
        Integer fifthPlace = winningStatus.countOfPlace(5);

        // when
        winningStatus.recordWinning(5, 1);

        // then
        assertThat(winningStatus.countOfPlace(5)).isEqualTo(fifthPlace);
        assertThat(winningStatus.countOfPlace(4)).isEqualTo(fourthPlace);
        assertThat(winningStatus.countOfPlace(3)).isEqualTo(thirdPlace);
        assertThat(winningStatus.countOfPlace(2)).isEqualTo(secondPlace + 1);
        assertThat(winningStatus.countOfPlace(1)).isEqualTo(firstPlace);
    }

    @Test
    @DisplayName("일치하는 숫자가 6개라면 1등 당첨 수가 증가한다.")
    void firstPlace() {
        // given
        Integer firstPlace = winningStatus.countOfPlace(1);
        Integer secondPlace = winningStatus.countOfPlace(2);
        Integer thirdPlace = winningStatus.countOfPlace(3);
        Integer fourthPlace = winningStatus.countOfPlace(4);
        Integer fifthPlace = winningStatus.countOfPlace(5);

        // when
        winningStatus.recordWinning(6, 0);

        // then
        assertThat(winningStatus.countOfPlace(5)).isEqualTo(fifthPlace);
        assertThat(winningStatus.countOfPlace(4)).isEqualTo(fourthPlace);
        assertThat(winningStatus.countOfPlace(3)).isEqualTo(thirdPlace);
        assertThat(winningStatus.countOfPlace(2)).isEqualTo(secondPlace);
        assertThat(winningStatus.countOfPlace(1)).isEqualTo(firstPlace + 1);
    }

    @Test
    @DisplayName("일치하는 숫자가 3개 미만이라면 당첨 수가 증가하지 않는다.")
    void failToPrize() {
        // given
        Integer firstPlace = winningStatus.countOfPlace(1);
        Integer secondPlace = winningStatus.countOfPlace(2);
        Integer thirdPlace = winningStatus.countOfPlace(3);
        Integer fourthPlace = winningStatus.countOfPlace(4);
        Integer fifthPlace = winningStatus.countOfPlace(5);

        // when
        winningStatus.recordWinning(2, 0);

        // then
        assertThat(winningStatus.countOfPlace(5)).isEqualTo(fifthPlace);
        assertThat(winningStatus.countOfPlace(4)).isEqualTo(fourthPlace);
        assertThat(winningStatus.countOfPlace(3)).isEqualTo(thirdPlace);
        assertThat(winningStatus.countOfPlace(2)).isEqualTo(secondPlace);
        assertThat(winningStatus.countOfPlace(1)).isEqualTo(firstPlace);
    }

    @ParameterizedTest(name = "{0} * {1}등 당첨 금액 = {2}")
    @CsvSource({
            "5, 5, 25000, 3, 0",
            "6, 4, 300000, 4, 0",
            "4, 3, 6000000, 5, 0",
            "3, 2, 90000000, 5, 1",
            "1, 1, 2000000000, 6, 0"
    })
    void calculatePrizeMoneyTest(Integer countOfWinning, Integer prize, Long totalPrizeMoney,
                                 Integer countOfMatchedLottoNumber, Integer countOfMatchedBonusNumber) {
        // given
        for (int i = 0; i < countOfWinning; i++) {
            winningStatus.recordWinning(countOfMatchedLottoNumber, countOfMatchedBonusNumber);
        }

        // when & then
        assertThat(winningStatus.calculatePrizeMoney(prize)).isEqualTo(totalPrizeMoney);
    }

    @ParameterizedTest()
    @CsvSource({
            "2147483, 1, 6, 0",
            "0, 5, 3, 0"
    })
    @DisplayName("당첨금을 0원 이상 4294966000000000원 이하로 수령했다면 어떠한 Exception도 감지되지 않는다.")
    void supportedPrizeMoney(Integer countOfWinning, Integer prize,
                             Integer countOfMatchedLottoNumber, Integer countOfMatchedBonusNumber) {
        // given
        for (int i = 0; i < countOfWinning; i++) {
            winningStatus.recordWinning(countOfMatchedLottoNumber, countOfMatchedBonusNumber);
        }

        // when & then
        assertThatNoException().isThrownBy(() -> winningStatus.calculatePrizeMoney(prize));
    }

    @ParameterizedTest()
    @CsvSource({
            "2147484, 1, 6, 0",
    })
    @DisplayName("당첨금을 4294966000000000원 초과로 수령했다면 IllegalArgumentException이 반환된다.")
    void notSupportedPrizeMoney(Integer countOfWinning, Integer prize,
                                Integer countOfMatchedLottoNumber, Integer countOfMatchedBonusNumber) {
        // given
        for (int i = 0; i < countOfWinning; i++) {
            winningStatus.recordWinning(countOfMatchedLottoNumber, countOfMatchedBonusNumber);
        }

        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> winningStatus.calculatePrizeMoney(prize))
                .withMessage(INVALID_PRIZE_MONEY.getMessage());
    }
}