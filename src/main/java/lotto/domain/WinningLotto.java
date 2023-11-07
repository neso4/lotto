package lotto.domain;

import lotto.Lotto;

import java.util.List;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final Lotto bonusNumber;

    // 입력된 winningNumbers와 bonusNumber가 올바른지 검증 필요
    public WinningLotto(Lotto winningNumbers, Lotto bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }
    private int getMatchCount(Lotto ticket) {
        return (int) ticket.getNumbers().stream()
                .filter(winningNumbers.getNumbers()::contains)
                .count();
    }
    private boolean matchBonus(Lotto ticket) {
        return ticket.getNumbers().contains(bonusNumber.getNumbers().get(0));
    }

    // 당첨 결과 계산 로직 작성 필요
    public LottoResult compare(List<Lotto> tickets) {
        LottoResult result = new LottoResult();
        for (Lotto ticket : tickets) {
            int matchCount = getMatchCount(ticket);
            boolean matchBonus = matchBonus(ticket);
            result.add(LottoEnum.LottoRank.valueOf(matchCount, matchBonus));
        }
        return result;
    }
}
