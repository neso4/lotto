package lotto.service;

import lotto.model.Lotto;
import lotto.model.LottoPurchaseAmount;
import lotto.model.LottoTicketCount;
import lotto.service.lotto.LottoTicketService;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.constant.LottoConfig.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Nested
@DisplayName("로또 생성을 테스트")
class LottoTicketServiceTest {
    private LottoTicketService lottoTicketService;
    private int startInclusive;
    private int endInclusive;
    private int count;

    @BeforeEach
    void setUp() {
        lottoTicketService = new LottoTicketService();
        startInclusive = LOTTO_START_NUMBER.getValue();
        endInclusive = LOTTO_END_NUMBER.getValue();
        count = LOTTO_COUNT_NUMBER.getValue();
    }

    @Test
    @DisplayName("9000원을 구매하면 9개 티켓이 생성된다.")
    void test1() {
        LottoPurchaseAmount lottoPurchaseAmount = new LottoPurchaseAmount(9000);
        LottoTicketCount lottoTicketCount = lottoTicketService.calculateTicketCount(lottoPurchaseAmount);
        assertThat(lottoTicketCount.count()).isEqualTo(9);
    }

    @Test
    @DisplayName("로또 번호는 정확히 6개다.")
    void test2() {
        Lotto lotto = lottoTicketService.createLotto();
        List<Integer> lottoNumbers = lotto.numbers();
        assertEquals(count, lottoNumbers.size());
    }

    @Test
    @DisplayName("모든 로또 번호는 1과 45 사이의 값이어야 한다.")
    void test3() {
        Lotto lotto = lottoTicketService.createLotto();
        List<Integer> lottoNumbers = lotto.numbers();
        assertTrue(lottoNumbers.stream()
                .allMatch(n -> n >= startInclusive && n <= endInclusive));
    }

    @RepeatedTest(100)
    @DisplayName("로또 번호에 중복된 값이 없어야 한다.")
    void test4() {
        Lotto lotto = lottoTicketService.createLotto();
        List<Integer> lottoNumbers = lotto.numbers();
        assertTrue(lottoNumbers.stream()
                .filter(n -> Collections.frequency(lottoNumbers, n) > 1)
                .collect(Collectors.toSet())
                .isEmpty());
    }

    @Test
    @DisplayName("발행된 번호는 오름차순으로 정렬하여 저장한다.")
    void test5() {
        Lotto lotto = lottoTicketService.createLotto();
        List<Integer> lottoNumbers = lotto.numbers();
        assertTrue(IntStream.range(0, lottoNumbers.size() - 1)
                .allMatch(i -> lottoNumbers.get(i) < lottoNumbers.get(i + 1)));
    }
}