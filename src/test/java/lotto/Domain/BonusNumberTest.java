package lotto.Domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusNumberTest {
    @Test
    @DisplayName("음수를 받았을 경우")
    void createBonusNumber_Minus() {
        assertThatThrownBy(() -> new BonusNumber(-1, Arrays.asList(1,2,3,4,5,6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("범위 밖의 수를 받은 경우")
    void createBonusNumber_Range() {
        assertThatThrownBy(() -> new BonusNumber(52, Arrays.asList(1,2,3,4,5,6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호에 들어있는 수를 입력 받은 경우")
    void createBonusNumber_Duplicate() {
        assertThatThrownBy(() -> new BonusNumber(1, Arrays.asList(1,2,3,4,5,6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

}