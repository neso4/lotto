package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoBundleTest {

    @DisplayName("로또 묶음은 금액만큼 로또를 생성한다.")
    @Test
    void makeLotto() {
        // given
        LottoBundle bundle = new LottoBundle();
        int case1 = 10000;
        int case2 = 0;
        int case3 = -1000;

        // when
        Throwable result1 = catchThrowable(() -> {
            bundle.makeLotto(case1);
        });
        Throwable result2 = catchThrowable(() -> {
            bundle.makeLotto(case2);
        });
        Throwable result3 = catchThrowable(() -> {
            bundle.makeLotto(case3);
        });

        // then
        assertThat(result1).as("case1").doesNotThrowAnyException();
        assertThat(result2).as("case2").isInstanceOf(IllegalArgumentException.class);
        assertThat(result3).as("case3").isInstanceOf(IllegalArgumentException.class);
    }
}