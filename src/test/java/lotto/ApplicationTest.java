package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.utils.ErrorCode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
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
            assertThat(output()).contains(ErrorCode.IS_NOT_NUMBERIC.getMessage());
        });
    }

    @Test
    void 로또_입력_공백_예외_테스트(){
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4, 5,6");
            assertThat(output()).contains(ErrorCode.IS_NOT_NUMBERIC_AND_COMMA.getMessage());
        });
    }

    @Test
    void 로또_입력_문자_예외_테스트(){
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,a,6");
            assertThat(output()).contains(ErrorCode.IS_NOT_NUMBERIC_AND_COMMA.getMessage());
        });
    }

    @Test
    void 유효하지_않은_보너스_번호_테스트() {
        assertSimpleTest(() -> {
            run("1000", "1,2,3,4,5,6", "6" ,"&", "7");
            assertThat(output()).contains(ErrorCode.INVALID_BONUS.getMessage());
        });
    }

    @Test
    void 유효하지_않은_로또_갯수_테스트() {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5");
            assertThat(output()).contains(ErrorCode.INVALID_LOTTO_COUNT.getMessage());
        });
    }

    @Test
    void 구매금액_입력되지_않은_상태에서_실행() {
        assertSimpleTest(() -> {
            runException("", "");
            assertThat(output()).contains(ErrorCode.INPUT_IS_NULL.getMessage());
        });
    }

    @Test
    void 로또번호_입력되지_않은_상태에서_실행() {
        assertSimpleTest(() -> {
            runException("1000", "", "");
            assertThat(output()).contains(ErrorCode.INPUT_IS_NULL.getMessage());
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
