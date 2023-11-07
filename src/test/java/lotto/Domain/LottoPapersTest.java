package lotto.Domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoPapersTest {

    private LottoPapers lottoPapers;
    private static final int AMOUNT = 5;

    @BeforeEach
    void setLottoPapers(){
        lottoPapers = new LottoPapers(AMOUNT);
    }

    @Test
    @DisplayName("로또 티켓 수가 올바르게 생성되는지 확인")
    void shouldGenerateCorrectNumberOfLottoPapers() {
        assertEquals(AMOUNT, lottoPapers.getLottoPapers().size());
    }

    @Test
    @DisplayName("로또 번호가 유효한 범위 내에 있는지 확인")
    void shouldGenerateValidLottoNumbers() {
        for (Lotto lotto : lottoPapers.getLottoPapers()) {
            for (int number : lotto.getNumbers()) {
                assertTrue(number >= 1 && number <= 45);
            }
        }
    }

    @Test
    @DisplayName("로또 번호가 올바른 갯수만큼 있는지 확인")
    void shouldGenerateUniqueAndCorrectNumberOfLottoNumbers() {
        for (Lotto lotto : lottoPapers.getLottoPapers()) {
            assertEquals(6, lotto.getNumbers().size());
        }
    }

    @Test
    @DisplayName("로또 번호가 오름차순으로 정렬되어 있는지 확인")
    void shouldGenerateLottoNumbersInAscendingOrder() {
        for (Lotto lotto : lottoPapers.getLottoPapers()) {
            List<Integer> sortedNumbers = new ArrayList<>(lotto.getNumbers());
            Collections.sort(sortedNumbers);
            assertEquals(sortedNumbers, lotto.getNumbers());
        }
    }
}