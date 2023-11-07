package lotto.game;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GameControllerTest {
    Game game = new Game();

    @Test
    void 로또_번호는_오름차순으로_정렬하여_출력() {
        // given
        int price = 1000;
        // when & then
        assertRandomUniqueNumbersInRangeTest(
            () -> {
                game.purchaseLotto(price);
                assertThat(game.getPurchasedLottosInfo()).contains(
                    "1개를 구매했습니다.",
                    "[8, 21, 23, 41, 42, 43]"
                );
            },
            List.of(8, 23, 21, 41, 42, 43)
        );
    }

    @Test
    void 당첨_내역_출력() {
        // given
        int price = 4000;
        // when & then
        assertRandomUniqueNumbersInRangeTest(
            () -> {
                game.purchaseLotto(price);
                game.setWinningLotto(List.of(8, 21, 23, 11, 16, 32), 7);
                assertThat(game.getLottoWinningResult()).contains(
                    "3개 일치 (5,000원) - 2개",
                    "4개 일치 (50,000원) - 0개",
                    "5개 일치 (1,500,000원) - 0개",
                    "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                    "6개 일치 (2,000,000,000원) - 0개",
                    "총 수익률은 250.0%입니다."
                );
            },
            List.of(8, 23, 21, 41, 42, 43),
            List.of(3, 5, 11, 16, 32, 38),
            List.of(7, 11, 16, 35, 36, 44),
            List.of(1, 8, 11, 31, 41, 42)
        );
    }

    @Test
    void 수익률은_소수점_둘째_자리에서_반올림() {
        // given
        int price = 9000;
        // when & then
        assertRandomUniqueNumbersInRangeTest(
            () -> {
                game.purchaseLotto(price);
                game.setWinningLotto(List.of(7, 8, 9, 10, 11, 12), 13);
                assertThat(game.getLottoWinningResult()).contains(
                    "총 수익률은 55.6%입니다."
                );
            },
            List.of(1, 2, 3, 4, 5, 6),
            List.of(1, 2, 3, 4, 5, 6),
            List.of(1, 2, 3, 4, 5, 6),
            List.of(1, 2, 3, 4, 5, 6),
            List.of(1, 2, 3, 4, 5, 6),
            List.of(1, 2, 3, 4, 5, 6),
            List.of(1, 2, 3, 4, 5, 6),
            List.of(1, 2, 3, 4, 5, 6),
            List.of(1, 2, 3, 9, 8, 7)
        );
    }
}
