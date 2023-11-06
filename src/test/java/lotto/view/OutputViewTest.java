package lotto.view;

import static lotto.view.ErrorMessage.NOT_NUMBER;
import static lotto.view.OutputView.printErrorMessage;
import static lotto.view.OutputView.printLottoNumbers;
import static lotto.view.OutputView.printNumOfTickets;
import static lotto.view.OutputView.printSystemMessage;
import static lotto.view.SystemMessage.ASK_BONUS;
import static lotto.view.SystemMessage.ASK_MONEY;
import static lotto.view.SystemMessage.ASK_WINNING_NUMBERS;
import static lotto.view.SystemMessage.WINNING_RESULT;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class OutputViewTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
        output.reset();
    }

    @DisplayName("시스템 메세지 출력 테스트")
    @ParameterizedTest
    @MethodSource("provideSystemMessage")
    void 출력테스트_printSystemMessage(SystemMessage systemMessage) {
        //given
        String expect = systemMessage.getMessage() + "\n";
        //when
        printSystemMessage(systemMessage);
        //then
        assertThat(output.toString()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideSystemMessage() {
        return Stream.of(
                Arguments.of(ASK_MONEY),
                Arguments.of(ASK_WINNING_NUMBERS),
                Arguments.of(ASK_BONUS),
                Arguments.of(WINNING_RESULT)
        );
    }

    @DisplayName("출력 (구입 금액 예외처리)")
    @Test
    void 출력테스트_printErrorMessage() {
        //given
        String expect = "[ERROR] 유효한 숫자를 입력해야 합니다.\n";
        //when
        printErrorMessage(new IllegalArgumentException(NOT_NUMBER.getMessage()));
        //then
        assertThat(output.toString()).isEqualTo(expect);
    }

    @DisplayName("출력 (티켓 개수 출력)")
    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4, 5, 2000000})
    void 출력테스트_printNumOfTickets(long input) {
        //given
        String expect = "\n" + input + "개를 구매했습니다.\n";
        //when
        printNumOfTickets(input);
        //then
        assertThat(output.toString()).isEqualTo(expect);
    }

    @DisplayName("출력 (로또 번호 출력)")
    @Test
    void 출력테스트_printNumOfTickets() {
        //given
        List<Integer> input = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        String expect = "[1, 2, 3, 4, 5, 6]\n";
        //when
        printLottoNumbers(input);
        //then
        assertThat(output.toString()).isEqualTo(expect);
    }
}
