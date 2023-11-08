package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.dto.LottoInfos;
import lotto.utils.ValidationUtil;

public class LottoService {
    private Lottos lottos;

    private final NumberPickingStrategy numberPickingStrategy;

    public LottoService(NumberPickingStrategy numberPickingStrategy) {
        this.numberPickingStrategy = numberPickingStrategy;
        this.lottos = new Lottos();
    }

    public LottoInfos createLottos(String money) {
        validate(money);
        List<Lotto> createdLottos = createLottoByCount(toLottoCount(money));
        return toLottoInfos(createdLottos);
    }

    private List<Lotto> createLottoByCount(int lottoCnt) {
        List<Lotto> createdLottos = new ArrayList<>();
        for (int i = 0; i < lottoCnt; i++) {
            Lotto createdLotto = lottos.createLotto(numberPickingStrategy);
            createdLottos.add(createdLotto);
        }
        return createdLottos;
    }

    private LottoInfos toLottoInfos(List<Lotto> createdLottos) {
        List<LottoInfo> createdLottoNums = new ArrayList<>();
        for (Lotto lotto : createdLottos) {
            createdLottoNums.add(new LottoInfo(lotto.getNumbers()));
        }
        return new LottoInfos(createdLottoNums);
    }

    private int toLottoCount(String money) {
        return Integer.parseInt(money) / 1000;
    }

    private void validate(String money) {
        ValidationUtil.validateIsMoneyDigit(money);
        ValidationUtil.validateIsMoneyThousandUnit(money);
    }
}
