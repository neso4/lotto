package lotto;

import static org.assertj.core.api.Assertions.*;

import lotto.core.Lotto;
import lotto.core.LottoGame;
import lotto.util.LottoConst;
import lotto.util.RandomNumGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGameTest {

    private static final int FIRST_NUM = 1;
    private static final int LAST_NUM = 6;

    @DisplayName("로또 번호 생성 시 중복 생기지 않는지 테스트")
    @Test
    void generatedNumberDuplicatedTest() {
        LottoGame lottoGame = new LottoGame();
        lottoGame.setNumOfLotto(1);
        lottoGame.makeLottos();

        // Lotto 인스턴스 생성 시 Lotto.validate() 통과
        Lotto lotto = lottoGame.makeLotto(new RandomNumGenerator(LottoConst.FIRST_NUM, LottoConst.LAST_NUM));
    }

    @DisplayName("로또 발급 시 예외 발생하지 않는지 테스트")
    @Test
    void generatedLottoSizeTest() {
        LottoGame lottoGame = new LottoGame();
        lottoGame.setNumOfLotto(1);
        lottoGame.makeLotto(new RandomNumGenerator(FIRST_NUM, LAST_NUM));
    }

    @DisplayName("로또 개수가 구매 개수와 같은지 테스트")
    @Test
    void generatedNumOfLottosTest() {
        LottoGame lottoGame = new LottoGame();
        lottoGame.setNumOfLotto(5);
        lottoGame.makeLottos();
        assertThat(lottoGame.issueLottos().count()).isEqualTo(5);
    }
}