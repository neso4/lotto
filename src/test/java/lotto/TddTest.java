package lotto;

import lotto.controller.LottoController;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.service.LottoService;
import lotto.util.RandomNumbers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TddTest {

    @Test
    public void 로또는_한장에_천원() {
        LottoController lottoController = new LottoController();
        int lottoCount = lottoController.calculateLottoCount(8000);

        assertThat(lottoCount).isEqualTo(8);
    }

    @Test
    public void 로또_구매입력이_1000원_단위가_아닐때() {
        LottoController lottoController = new LottoController();

        int money = 1500;
        assertThatThrownBy(() -> {
            lottoController.calculateLottoCount(money);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또는 1000원 단위로만 구매할 수 있습니다.");
    }

    @Test
    public void _1부터_45사이_로또번호_생성_테스트() {
        List<Integer> numbers = RandomNumbers.generateRandomNumbers();

        for (int number : numbers) {
            assertThat(number).isBetween(1, 45);
        }
    }

    @Test
    public void 로또_번호는_6자리() {
        List<Integer> numbers = RandomNumbers.generateRandomNumbers();
        Assertions.assertThat(numbers).hasSize(6);
    }

    @Test
    public void 로또_번호_5자리_오류() {
        List<Integer> numbers = RandomNumbers.generateRandomNumbers();

        assertThrows(AssertionError.class, () -> {
            assertThat(numbers.size()).isEqualTo(5);
        });
    }

    @Test
    public void 당첨번호는_반점_기준으로_6자리_입력() {
        List<Integer> expectedWinningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> WinningNumbers = parseNumbers("1,2,3,4,5,6");
        assertThat(WinningNumbers).isEqualTo(expectedWinningNumbers);
    }

    @Test
    public void 로또_번호가_같은게_있으면_예외처리() {
        List<Lotto> lottoList = new ArrayList<>();
        lottoList.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottoList.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));

        assertThatThrownBy(() -> new Lottos(lottoList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호가 중복되었습니다.");
    }

    @Test
    public void HashMap값_확인() {
        List<Lotto> lottoList = new ArrayList<>();
        lottoList.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottoList.add(new Lotto(Arrays.asList(7, 8, 9, 10, 11, 12)));
        lottoList.add(new Lotto(Arrays.asList(13, 14, 15, 16, 17, 18)));

        Lottos lottos = new Lottos(lottoList);

        Map<Long, Long> testMap = lottos.checkWinningNumbers(Arrays.asList(1, 2, 3, 4, 5, 8), 9);
        for (Map.Entry<Long, Long> entry : testMap.entrySet()) {
            Long key = entry.getKey();
            Long value = entry.getValue();
            System.out.println("당첨 개수: " + key + ", 당첨 횟수: " + value);
        }
    }

    @Test
    public void 로또_번호는_오름차순_정렬() {
        Lotto lotto = new Lotto(Arrays.asList(5, 20, 1, 42, 3, 59));

        assertThat(lotto.getNumbers()).isEqualTo(Arrays.asList(1, 3, 5, 20, 42, 59));
    }

    private List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Test
    public void 수익률_계산() {
        Map<Long, Long> winningResults = new HashMap<>();
        winningResults.put(3L, 1L); // 3개 일치 상금 5,000원
        winningResults.put(4L, 1L); // 4개 일치 상금 50,000원
        int buyCount = 5;

        LottoService lottoService = new LottoService();
        double profitRate = lottoService.calculateProfitRate(buyCount, winningResults);

        double expectedProfitRate = 1100.0;
        assertThat(profitRate).isEqualTo(expectedProfitRate);
    }

}
