package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.util.Converter;

public class LottoService {
    private final List<Lotto> lottos = new ArrayList<>();
    private WinningLotto winningLotto;
    private LottoResult lottoResult;
    private int purchaseAmount;

    public void buyLotto(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
        int numberOfLottos = purchaseAmount / 1000;
        generateLotto(numberOfLottos);
    }

    private void generateLotto(int numberOfLottos) {
        List<Integer> lottoNumbers;
        for (int i = 0; i < numberOfLottos; i++) {
            lottoNumbers = createLottoNumbers();
            lottos.add(new Lotto(lottoNumbers));
        }
    }

    private List<Integer> createLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }

    public List<String> createBuyResults() {
        return lottos.stream()
                .map(Lotto::getFormalizedNumbers)
                .collect(Collectors.toList());
    }

    public void makeWinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        winningLotto = new WinningLotto(winningNumbers, bonusNumber);
    }

    public List<String> createWinningResults() {
        lottoResult = new LottoResult(lottos, winningLotto);
        return lottoResult.getFormalizedResult();
    }
}
