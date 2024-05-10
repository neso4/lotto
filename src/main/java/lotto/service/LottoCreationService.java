package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.controller.NumberPickingStrategy;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.dto.LottoInfo;
import lotto.dto.LottoInfos;
import lotto.utils.StringUtil;
import lotto.utils.ValidationUtil;

public class LottoCreationService {
    private Lottos lottos;
    private Money totalMoney;
    private final NumberPickingStrategy numberPickingStrategy;

    public LottoCreationService(NumberPickingStrategy numberPickingStrategy) {
        this.numberPickingStrategy = numberPickingStrategy;
        this.lottos = new Lottos();
    }

    public LottoInfos createLottos(String money) {
        validate(money);
        totalMoney = new Money(Integer.parseInt(money));
        return toLottoInfos(createLottoByCount(toLottoCount(money)));
    }

    private LottoInfos toLottoInfos(List<Lotto> createdLottos) {
        return new LottoInfos(createdLottos.stream()
                .map(lotto -> new LottoInfo(lotto.getNumbers()))
                .toList()
        );
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

    public Lottos getLottos() {
        return lottos;
    }

    public Money getTotalMoney() {
        return totalMoney;
    }
}
