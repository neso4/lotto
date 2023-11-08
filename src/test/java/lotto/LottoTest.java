package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 1,000원으로 나누어 떨어 지지 않으면 예외가 발생한다.")
    @Test
    void inputPurchaseAmount() {
        assertThatThrownBy(() -> new LottoInput())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구입 금액은 1,000원 단위로 입력해야 합니다.");
    }

    @DisplayName("랜덤으로 로또 번호를 생성하여 저장")
    @Test
    void randomLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        Collections.sort(numbers);
        System.out.println(numbers);
    }

    @DisplayName("사용자가 구매한 수만큼 로또 번호 생성")
    @Test
    void generateLottoTickets() {
        int input = 5;
        List<List<Integer>> lottoTickets = new ArrayList<>();
        for (int i=0; i < input; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Collections.sort(numbers);
            lottoTickets.add(numbers);
        }

        for (List<Integer> ticket : lottoTickets) {
            System.out.println(ticket);
        }
    }

    @DisplayName("당첨 번호 확인")
    @Test
    void winningNumber() {
        LottoInput lottoInput = new LottoInput();
        List<Integer> winningNumbers = lottoInput.inputWinningNumbers();
        System.out.println(winningNumbers);
    }
}