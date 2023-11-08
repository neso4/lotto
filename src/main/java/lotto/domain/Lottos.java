package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import lotto.Lotto;
public class Lottos {
    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public  List<Rank> matchRanks(WinningLottoNumbers winningLottoNumbers) {
        return lottos.stream()
                .map(lotto -> mapToRank(lotto,winningLottoNumbers))
                .collect(Collectors.toList());
    }

    public Rank mapToRank(Lotto lotto, WinningLottoNumbers winningLottoNumbers) {
        int matchCount = lotto.matchCount(winningLottoNumbers.getWinningLotto());
        boolean isBonus = lotto.matchBonus(winningLottoNumbers);

        return Rank.isSecondMatch(matchCount, isBonus);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

}
