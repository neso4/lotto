package lotto.dto;

import java.util.List;

public class LottoInfos {
    private final List<LottoInfo> lottoInfos;
    public LottoInfos(List<LottoInfo> lottoInfos) {
        this.lottoInfos = lottoInfos;
    }

    public List<LottoInfo> getLottoInfos() {
        return lottoInfos;
    }
}
