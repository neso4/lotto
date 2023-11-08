package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 구입_금액이_1000원_단위_예외_테스트() {
        assertSimpleTest(() -> {
            runException("1500");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("당첨 번호가 숫자가 아닌 경우 예외 처리한다.")
    @Test
    void 당첨_번호_숫자_입력_예외_테스트1() {
        assertSimpleTest(() -> {
            runException("1000", "a,b,c,d,e,f");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("당첨 번호가 6개 이상 입력된 경우 예외 처리한다.")
    @Test
    void 당첨_번호_숫자_입력_예외_테스트2() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,6,7");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("당첨 번호에 45가 넘는 숫자를 입력하는 경우")
    @Test
    void 범위_외_숫자_입력_예외_테스트1() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,46");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("당첨 번호에 0을 입력하는 경우")
    @Test
    void 범위_외_숫자_입력_예외_테스트2() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,0");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("당첨 번호에 0보다 작은 수를 입력하는 경우")
    @Test
    void 범위_외_숫자_입력_예외_테스트3() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,-50");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("보너스 번호에 45보다 큰 수를 입력하는 경우")
    @Test
    void 범위_외_숫자_입력_예외_테스트_보너스1() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,6", "100");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("보너스 번호에 0을 입력하는 경우")
    @Test
    void 범위_외_숫자_입력_예외_테스트_보너스2() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,6", "0");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("보너스 번호에 0보다 작은 수를 입력하는 경우")
    @Test
    void 범위_외_숫자_입력_예외_테스트_보너스3() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,6", "-100");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("당첨 번호에 중복 입력된 숫자가 있는 경우 예외 처리한다.")
    @Test
    void 중복_입력_예외_테스트() {
        assertSimpleTest(() -> {
            runException("1000", "1,1,2,3,4,5");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("보너스 번호 입력 시에 중복 입력된 숫자가 있는 경우 예외 처리한다.")
    @Test
    void 중복_입력_예외_테스트2() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,6", "1");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
