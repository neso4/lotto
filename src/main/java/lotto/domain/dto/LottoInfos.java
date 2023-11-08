package lotto.domain.dto;

import java.util.List;
import lotto.domain.LottoInfo;

public class LottoInfos {
    private final List<LottoInfo> lottoInfos;
    public LottoInfos(List<LottoInfo> lottoInfos) {
        this.lottoInfos = lottoInfos;
    }

    public List<LottoInfo> getLottoInfos() {
        return lottoInfos;
    }
}
