package lotto.view;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import lotto.Application;
import org.junit.jupiter.api.Test;

class OutputViewTest extends NsTest {

    @Test
    void outputLottoNumbersBySortedAsc() {
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
                            "[1, 3, 5, 14, 22, 45]"
                    );
                },
                List.of(42, 21, 23, 41, 8, 43),
                List.of(3, 5, 32, 16, 11, 38),
                List.of(7, 36, 16, 35, 11, 44),
                List.of(1, 42, 11, 31, 41, 8),
                List.of(45, 14, 16, 38, 42, 13),
                List.of(7, 11, 40, 30, 42, 43),
                List.of(2, 13, 38, 32, 22, 45),
                List.of(1, 3, 45, 14, 22, 5)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}