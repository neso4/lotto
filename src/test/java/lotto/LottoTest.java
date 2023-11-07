package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

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
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 1~45 범위 이외의 숫자가 포함되면 예외가 발생한다.")
    @Test
    void createLottoByNumberOutOfRange() {
        assertThatThrownBy(() -> new Lotto(List.of(13, 24, 67, 90, 35, 34)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("검증 로직이 포함된 Lotto 생성자를 통해 랜덤 숫자를 반환하는 메서드룰 다회 검증한다.")
    @RepeatedTest(value = 15)
    void createRandomNumbersForLotto() {
        createLottoByNumberOutOfRange();
    }
}