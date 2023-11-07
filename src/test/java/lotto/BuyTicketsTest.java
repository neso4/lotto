package lotto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;
import static org.assertj.core.api.Assertions.assertThat;

class BuyTicketsTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @BeforeEach
    void InitTest() {
        SystemIO systemIO = new SystemIO();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("입력값은 숫자여야 한다.")
    void validateInputIsIntegerTest() {
        String purchaseAmount = "String INPUT";
        InputStream byteOfPurchaseAmount = new ByteArrayInputStream(purchaseAmount.getBytes());
        System.setIn(byteOfPurchaseAmount);
        new BuyTickets();
        assertThat(outputStreamCaptor.toString().trim()).contains("ERROR");
    }

    @Test
    @DisplayName("입력값은 1000의 배수여야 한다.")
    void validateInputIsMultipleNumberOfThousand() {
        String purchaseAmount = "1200";
        Assertions.assertThrows(IllegalStateException.class, () -> new BuyTickets());
    }

    @Test
    @DisplayName("입력한 만큼의 티켓이 구입되었는지 확인")
    void testNumberOfTickets() {
        Map<Long, Integer> ticketAmounts = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            ticketAmounts.put(Long.valueOf(i), pickNumberInRange(1, 99));
        }
        for (Long key : ticketAmounts.keySet()) {
            String buyAmounts = String.valueOf(ticketAmounts.get(key)*1000);
            BuyTickets buyTickets = new BuyTickets();
            assertThat(buyTickets.getLotteryNumbers().size()).isEqualTo(ticketAmounts.get(key));
        }
    }
}