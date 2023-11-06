package lotto.domain.seller;

import lotto.config.Config;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoEnvelop;

public class LottoSeller {
    // TODO: 11/6/23 상수 관리
    private static final Integer PRICE_LOTTO = 1000;
    private LottoEnvelop lottoEnvelop;

    public LottoSeller() {
        this.lottoEnvelop = Config.lottoEnvelop();
    }

    public LottoEnvelop sell(Integer money) {
        makeLottoEnvelope(money);

        return lottoEnvelop;
    }

    public void makeLottoEnvelope(Integer money) {
        Integer sizeLottoEnvelop = getSizeLottoEnvelop(money);
        Lotto lotto = null;

        for (int index = 0; index < sizeLottoEnvelop; index++) {
            lotto = createLotto();
            addLottoEnvelop(lotto);
        }
    }

    // TODO: 11/6/23 상수 정리 
    public String tellNumberLotto() {
        return lottoEnvelop.size() + "개를 구매했습니다.";
    }

    private Lotto createLotto() {
        return Config.lotto();
    }

    private void addLottoEnvelop(Lotto lotto) {
        lottoEnvelop.add(lotto);
    }

    private int getSizeLottoEnvelop(Integer money) {
        return money / PRICE_LOTTO;
    }
}
