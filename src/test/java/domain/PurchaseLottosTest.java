package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseLottosTest {
    @DisplayName("구매한 만큼 로또 목록을 생성합니다. - 3개의 로또가 모두 서로 다른 숫자로 이루어졌는지 확인합니다.")
    @Test
    void createRandomLottoNumbers() {
        PurchaseLottos purchaseLottos = new PurchaseLottos(3);

        for (int index = 0; index < purchaseLottos.getPurchaseLottos().size(); index++) {
            List<Integer> lotto = purchaseLottos.getPurchaseLottos().get(index).getNumbers();

            assertThat(lotto.size()).isEqualTo(6);
            assertThat(lotto.stream().distinct().count() == lotto.size()).isTrue();
            assertThat(lotto.stream().allMatch(number -> number >= 1 && number <= 45)).isTrue();
        }
    }
}
