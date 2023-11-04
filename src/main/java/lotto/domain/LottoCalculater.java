package lotto.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class LottoCalculater {

    private User user;
    private LottoAdmin lottoAdmin;

    private final Map<LottoResult, Integer> lottoResult;

    public LottoCalculater(User user, LottoAdmin lottoAdmin) {
        this.user = user;
        this.lottoAdmin = lottoAdmin;
        lottoResult = new EnumMap<LottoResult, Integer>(LottoResult.class);
        Arrays.stream(LottoResult.values())
                .forEach(prize -> lottoResult.put(prize, 0));
    }

    public void calculateResult() {
        for (Lotto lotto : user.getLottos()) {
            LottoResult result = LottoResult.getLottoResult(lotto.getMatchLottoNumber(lottoAdmin),
                    lotto.isContain(lottoAdmin.getBonusNumber()));
            updateLottoResultCount(result);
        }
    }

    private void updateLottoResultCount(LottoResult result) {
        lottoResult.put(result, this.lottoResult.get(result) + 1);
    }

    public Integer getLottoResultCount(LottoResult result) {
        return lottoResult.get(result);
    }


}
