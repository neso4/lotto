package lotto.domain;

import java.util.List;
import lotto.model.Lotto;
import lotto.util.WinningResult;

public interface LottoService {

    /**
     * 로또 개수를 저장하고 로또를 개수만큼 발행한다
     * @param amount 로또 구매 금액
     */
    public void saveUser(int amount);

    /**
     * 로또 개수를 조회한다
     * @return 로또 개수
     */
    public int getCount();

    /**
     * 로또 번호(들을)를 조회한다
     * @return 로또 번호 목록
     */
    public List<Lotto> getLottos();

    /**
     * 로또 당첨 결과를 확인한다
     * @return 로또 당첨 결과 목록
     */
    public List<WinningResult> checkWinningResult();

    /**
     * 로또 수익률을 확인한다
     * @return 로또 수익률 (소수점 2자리에서 반올)
     */
    public double checkEarningRate(List<WinningResult> results);
}
