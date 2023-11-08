package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.NumberPickingStrategy;
import lotto.utils.StringUtil;
import lotto.utils.ValidationUtil;

public class LottoCreationService {
    private Lottos lottos;
    private final NumberPickingStrategy numberPickingStrategy;

    public LottoCreationService(NumberPickingStrategy numberPickingStrategy) {
        this.numberPickingStrategy = numberPickingStrategy;
        this.lottos = new Lottos();
    }

    public List<Lotto> createLottos(String money) {
        validate(money);
        return createLottoByCount(toLottoCount(money));
    }

    private List<Lotto> createLottoByCount(int lottoCnt) {
        List<Lotto> createdLottos = new ArrayList<>();
        for (int i = 0; i < lottoCnt; i++) {
            Lotto createdLotto = lottos.createLotto(numberPickingStrategy);
            createdLottos.add(createdLotto);
        }
        return createdLottos;
    }

    private int toLottoCount(String money) {
        return StringUtil.divideByOneThousand(money);
    }

    private void validate(String money) {
        ValidationUtil.validateIsMoneyDigit(money);
        ValidationUtil.validateIsMoneyThousandUnit(money);
    }
}
