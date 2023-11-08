package lotto.io;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import lotto.domain.LottoBall;
import lotto.domain.MatchDetail;
import lotto.domain.WinningNumbers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserLottoInputTest {
    public void setupInputStream(String inputString) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(byteArrayInputStream);
    }

    UserLottoInput input = new UserLottoInput();

    @ParameterizedTest
    @ValueSource(strings = {"100b\n", "1000 \n", "10 a\n", "-1\n", "0\n", "2100000000000", "\n"})
    @DisplayName("올바르지 않은 돈 입력이 들어오면 예외를 발생한다.")
    public void invalidMoneyInput(String in) {
        setupInputStream(in);

        assertThatThrownBy(input::getMoneyAmount)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"100\n", "1000\n", "12345\n"})
    @DisplayName("올바르게 입력된 돈이 정확하게 반환된다.")
    public void validMoneyInput(String in){
        setupInputStream(in);

        long moneyAmount = input.getMoneyAmount();
        assertThat(moneyAmount).isEqualTo(Long.parseLong(in));
    }

    @Test
    @DisplayName("입력을 통해 올바르게 당첨 로또를 받아낸다.")
    public void getWinningNumbers() {
        // given
        setupInputStream("1,2,3,4,5,6\n7");
        // when
        Lotto playerLotto = input.getLotto();
        LottoBall bonusNumber = input.getBall();
        WinningNumbers winningNumbers = new WinningNumbers(playerLotto, bonusNumber);
        // then
        Lotto lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 7)
                .map(LottoBall::new)
                .toList());
        MatchDetail match = winningNumbers.match(lotto);

        assertThat(match.matchedBonus()).isTrue();
        assertThat(match.matchedCount()).isEqualTo(5);
    }

    @Test
    @DisplayName("빈 줄이 입력으로 들어오는 경우 예외를 발생한다.")
    public void noneInputException(){
        // given
        setupInputStream("\n");
        // when, then
        assertThatThrownBy(input::getMoneyAmount)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {",,,,,\n", "1,,2,3,4,5\n", "1,2,3,4,5\n", "1,2,3,4,5,6,7\n", "1,2,3,3,4,5\n"})
    @DisplayName("로또 번호를 올바르게 입력하지 않는 경우 예외를 발생한다.")
    public void illegalLottoInput(String in){
        // given
        setupInputStream(in);
        // when, then
        assertThatThrownBy(input::getLotto)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @AfterEach
    public void tearDown() {
        Console.close();
    }
}
