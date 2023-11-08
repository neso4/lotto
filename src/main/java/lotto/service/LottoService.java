package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.PurchasedLotto;
import lotto.domain.WinningLotto;

public class LottoService {

    private final LottoNumberGenerator lottoGenerator;

    private LottoService() {
        lottoGenerator = new LottoNumberGenerator();
    }

    public static LottoService startGame() {
        return new LottoService();
    }

    public PurchasedLotto createLotto(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for(int i = 0; i < lottoCount; i++) {
            List<Integer> numbers = lottoGenerator.generateLotto();
            Lotto lotto = new Lotto(numbers);
            lottos.add(lotto);
        }
        return new PurchasedLotto(lottos);
    }

    public List<LottoResult> getLottoResultList(PurchasedLotto purchased, WinningLotto winningLotto) {
        return purchased.getLottos().stream()
                .map(lotto -> LottoResult.calculate(lotto, winningLotto))
                .toList();
    }
}
