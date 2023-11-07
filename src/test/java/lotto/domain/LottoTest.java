package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    // 아래에 추가 테스트 작성 가능
    @Test
    void 번호_3개_일치할_때_가격_5000원() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Ranking ranking = lotto.getRanking(List.of(1, 2, 3, 7, 8, 9), 4);
        assertEquals("5,000", ranking.getPrize());
    }

    @Test
    void 번호_4개_일치할_때_가격_50000원() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Ranking ranking = lotto.getRanking(List.of(1, 2, 3, 4, 7, 8), 9);
        assertEquals("50,000", ranking.getPrize());
    }

    @Test
    void 번호_5개_일치할_때_가격_1500000원() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Ranking ranking = lotto.getRanking(List.of(1, 2, 3, 4, 5, 8), 9);
        assertEquals("1,500,000", ranking.getPrize());
    }

    @Test
    void 번호_5개_및_보너스_번호_일치할_때_가격_30000000원() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Ranking ranking = lotto.getRanking(List.of(1, 2, 3, 4, 5, 8), 6);
        assertEquals("30,000,000", ranking.getPrize());
    }

    @Test
    void 번호_6개_일치할_때_가격_2000000000원() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Ranking ranking = lotto.getRanking(List.of(1, 2, 3, 4, 5, 6), 7);
        assertEquals("2,000,000,000", ranking.getPrize());
    }

    @Test
    void 문자열_생성_테스트() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertEquals("[1, 2, 3, 4, 5, 6]", lotto.toString());
    }
}